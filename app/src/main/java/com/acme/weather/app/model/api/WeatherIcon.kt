package com.acme.weather.app.model.api

import com.acme.weather.R

sealed class WeatherIcon(val description: String,
                         val iconResourceId: Int,
                         val backgroundColorResourceId: Int) {

    companion object {
        /**
         * Find proper WeatherIcon for description, or default to SUNNY.
         */
        fun fromDescription(description: String?) = when (description) {
            "clear-day", "clear-night" -> Sunny(description)
            "snow", "sleet" -> Snowy(description)
            "rain" -> Rainy(description)
            "hail", "thunderstorm", "tornado" -> Thunderstorm(description)
            "wind", "fog", "cloudy" -> Cloudy(description)
            "partly-cloudy-day", "partly-cloudy-night" -> PartlyCloudy(description)
            else -> Sunny("clear-day")
        }
    }
}

class Sunny(desc: String) : WeatherIcon(desc, R.mipmap.ic_sunny, R.color.bg_sunny)
class Snowy(desc: String) : WeatherIcon(desc, R.mipmap.ic_snowy, R.color.bg_snowy)
class Rainy(desc: String) : WeatherIcon(desc, R.mipmap.ic_rainy, R.color.bg_rainy)
class Thunderstorm(desc: String) : WeatherIcon(desc, R.mipmap.ic_thunderstorm, R.color.bg_thunderstorm)
class Cloudy(desc: String) : WeatherIcon(desc, R.mipmap.ic_cloudy, R.color.bg_cloudy)
class PartlyCloudy(desc: String) : WeatherIcon(desc, R.mipmap.ic_partlycloudy, R.color.bg_partlycloudy)
