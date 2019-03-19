package com.acme.weather.app.model.repository.network

data class WeatherApiResponse(
        var latitude: String?,
        var longitude: String?,
        var currently: Currently?,
        var daily: Daily?)

data class Currently(
        var time: Long?,
        var icon: String?,
        var temperature: Double?)

data class Daily(
        var summary: String?,
        var data: List<ExtraData>?)

data class ExtraData(
        var time: Long?,
        var temperatureHigh: Double?,
        var temperatureLow: Double?)
