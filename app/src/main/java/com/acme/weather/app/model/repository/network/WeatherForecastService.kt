package com.acme.weather.app.model.repository.network

import com.acme.weather.app.model.api.ForecastData
import com.acme.weather.app.model.api.Temperature
import com.acme.weather.app.model.api.WeatherIcon
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherForecastService @Inject constructor(private val weatherApi: WeatherApi) {

    /**
     * Blocking call to get weatherList forecast data given (lat,long) coordinates.
     */
    fun getWeatherForecast(latitude: String, longitude: String): ForecastData {

        val latLong = "${latitude},${longitude}"
        val apiResp = weatherApi
                .getWeatherForCoordinate(latLong)
                .blockingGet()

        return responseToDto(apiResp)
    }

    private fun responseToDto(apiResp: WeatherApiResponse): ForecastData {

        return ForecastData(
                forecast = apiResp.daily?.summary,

                weatherIcon = WeatherIcon.fromDescription(apiResp.currently?.icon),

                current = Temperature.fromFahrenheit(
                        apiResp.currently?.temperature?.roundToInt() ?: 0),

                high = Temperature.fromFahrenheit(
                        apiResp.daily?.data
                                ?.sortedByDescending { it.temperatureHigh }
                                ?.firstOrNull()
                                ?.temperatureHigh
                                ?.roundToInt() ?: 0),

                low = Temperature.fromFahrenheit(
                        apiResp.daily?.data
                                ?.sortedByDescending { it.temperatureLow }
                                ?.firstOrNull()
                                ?.temperatureLow
                                ?.roundToInt() ?: 0))
    }


}