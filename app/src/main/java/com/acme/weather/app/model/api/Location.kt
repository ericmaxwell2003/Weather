package com.acme.weather.app.model.api

data class Location(
    val zip: String,
    val latitude: String,
    val longitude: String,
    val locationName: String)