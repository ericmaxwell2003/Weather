package com.acme.weather.app.model.api

data class ForecastData(
    val current: Temperature? = null,
    val high: Temperature? = null,
    val low: Temperature? = null,
    val forecast: String? = null,
    val weatherIcon: WeatherIcon)
