package com.acme.weather.app.model.api

data class Weather(
        val id: Long?,
        val location: Location,
        val forecastData: ForecastData? = null)
