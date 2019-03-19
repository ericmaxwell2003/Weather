package com.acme.weather.model.api

data class Weather(
        val id: Long?,
        val location: Location,
        val forecastData: ForecastData? = null)
