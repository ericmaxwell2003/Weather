package com.acme.weather.app.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.acme.weather.app.model.api.*
import com.acme.weather.app.model.repository.database.dao.WeatherDao
import com.acme.weather.app.model.repository.database.entity.TemperatureEntity
import com.acme.weather.app.model.repository.database.entity.WeatherEntity
import com.acme.weather.app.model.repository.network.WeatherForecastService
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
        private val weatherDao: WeatherDao, private val weatherForecastService: WeatherForecastService) {

    val weatherList: LiveData<List<Weather>> by lazy {
        updateLocalCache() // refresh underlying data in remote service.
        fetchWeatherFromDatabase() // perform the LiveData query to return local results.
    }

    fun byIdentifier(id: Long) : LiveData<Weather> {
        return Transformations.map(weatherDao.byId(id), {
            entityToDto(it)
        })
    }

    fun byZipCode(zipCode: String) : LiveData<Weather> {
        return Transformations.map(weatherDao.byZip(zipCode), {
            entityToDto(it)
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
                val forecastData = weatherForecastService
                        .getWeatherForecast(latitude = w.latitude, longitude = w.longitude)
                applyForecast(w, forecastData)
                weatherDao.insertOrUpdate(w)
            }
        }
    }

    private fun fetchWeatherFromDatabase() : LiveData<List<Weather>> {
        return Transformations.map(weatherDao.findAll()) { weatherObjects: List<WeatherEntity> ->
            weatherObjects.map { entityToDto(it) }
        }
    }

    private fun entityToDto(it: WeatherEntity) : Weather {
        return Weather(
                id = it.id,
                location =  Location(
                        zip = it.zip,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        locationName = it.locationName
                ),
                forecastData = ForecastData(
                        current = entityToDto(it.currentTemp),
                        high = entityToDto(it.highTemp),
                        low = entityToDto(it.lowTemp),
                        weatherIcon = WeatherIcon.fromDescription(it.iconDescription),
                        forecast = it.forecast))
    }

    private fun entityToDto(it: TemperatureEntity) : Temperature {
        return Temperature(fahrenheit = it.fahrenheit, celsius = it.celsius)
    }

    private fun applyForecast(w: WeatherEntity, forecastData: ForecastData) {
        w.forecast = forecastData.forecast
        w.iconDescription = forecastData.weatherIcon.description
        w.currentTemp = TemperatureEntity().apply {
            fahrenheit = forecastData.current?.fahrenheit ?: 0
            celsius = forecastData.current?.celsius ?: 0
        }
        w.highTemp = TemperatureEntity().apply {
            fahrenheit = forecastData.high?.fahrenheit ?: 0
            celsius = forecastData.high?.celsius ?: 0
        }
        w.lowTemp = TemperatureEntity().apply {
            fahrenheit = forecastData.low?.fahrenheit ?: 0
            celsius = forecastData.low?.celsius ?: 0
        }
    }

}