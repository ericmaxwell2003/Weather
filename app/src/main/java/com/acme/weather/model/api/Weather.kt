package com.acme.weather.model.api

data class Weather(
        val id: Long?,
        val location: Location,

        val current: Temperature? = null,
        val high: Temperature? = null,
        val low: Temperature? = null,
        val forecast: String? = null,
        val weatherIcon: WeatherIcon = WeatherIcon.SUNNY,
        val showingFahrenheit: Boolean = true
)