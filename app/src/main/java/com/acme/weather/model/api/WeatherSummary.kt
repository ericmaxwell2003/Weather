package com.acme.weather.model.api

data class WeatherSummary (
    val id: Int,
    val zip: String,
    val current: Temp,
    val high: Temp,
    val low: Temp
)
