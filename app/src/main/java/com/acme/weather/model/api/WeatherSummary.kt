package com.acme.weather.model.api

data class WeatherSummary (
        val id: Int,
        val zip: String,
        val latitude: String? = null,
        val longitude: String? = null,
        val locationName: String? = null,
        val current: Temperature? = null,
        val high: Temperature? = null,
        val low: Temperature? = null
)
