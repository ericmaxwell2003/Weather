package com.acme.weather.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.acme.weather.model.api.Location
import com.acme.weather.model.api.Temperature
import com.acme.weather.model.api.Weather
import com.acme.weather.model.api.WeatherIcon
import com.acme.weather.model.repository.database.WeatherDatabase
import com.acme.weather.model.repository.database.entity.WeatherEntity
import com.acme.weather.model.repository.network.WeatherApi
import com.acme.weather.model.repository.network.WeatherApiResponse
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class WeatherRepository @Inject constructor(
        database: WeatherDatabase, private val weatherApi: WeatherApi) {

    private val weatherDao = database.weatherDao()

    val weatherList: LiveData<List<Weather>> by lazy {
        updateLocalCache() // refresh underlying data in remote service.
        fetchWeatherFromDatabase() // perform the LiveData query to return local results.
    }

    fun byIdentifier(id: Long) : LiveData<Weather> {
        return Transformations.map(weatherDao.byId(id), {
            mapWeatherEntityToWeatherSummaryApi(it)
        })
    }

    fun removeWeatherLocation(id: Long) {
        doAsync {
            weatherDao.delete(weatherDao.byIdSync(id))
        }
    }

    fun addWeatherLocation(location: Location) {

        doAsync {
            val entity = WeatherEntity().apply {
                zip = location.zip
                latitude = location.latitude
                longitude = location.longitude
                locationName = location.locationName
            }

            weatherDao.insertOrUpdate(entity)
            updateLocalCache()
        }
    }

    private fun updateLocalCache() {

        doAsync {
            val weather = weatherDao.findAllSync()
            weather.forEach { w ->
                val latLong = "${w.latitude},${w.longitude}"
                val apiResp = weatherApi
                        .getWeatherForCoordinate(latLong)
                        .blockingGet()
                updateWeatherFromApiResp(w, apiResp)
                weatherDao.insertOrUpdate(w)
            }
        }
    }

    private fun fetchWeatherFromDatabase() : LiveData<List<Weather>> {

        return Transformations.map(weatherDao.findAll(), { weatherObjects ->
            weatherObjects.map {
                mapWeatherEntityToWeatherSummaryApi(it)
            }
        })

    }

    private fun mapWeatherEntityToWeatherSummaryApi(it: WeatherEntity) : Weather {
        return Weather(
                id = it.id,
                location =  Location(
                        zip = it.zip,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        locationName = it.locationName
                ),
                current = Temperature.fromFahrenheit(it.currentTemp),
                high = Temperature.fromFahrenheit(it.highTemp),
                low = Temperature.fromFahrenheit(it.lowTemp),
                weatherIcon = WeatherIcon.fromDescription(it.iconDescription),
                forecast = it.forecast)
                
    }

    private fun updateWeatherFromApiResp(w: WeatherEntity, apiResp: WeatherApiResponse) {
        w.currentTemp = apiResp.currently.temperature.roundToInt()
        w.iconDescription = apiResp.currently.icon
        w.forecast = apiResp.daily.summary
        w.highTemp = apiResp.daily.data
                .sortedByDescending { it.temperatureHigh }
                .firstOrNull()
                ?.temperatureHigh
                ?.roundToInt() ?: 0
        w.lowTemp = apiResp.daily.data
                .sortedByDescending { it.temperatureLow }
                .firstOrNull()
                ?.temperatureLow
                ?.roundToInt() ?: 0
    }

}