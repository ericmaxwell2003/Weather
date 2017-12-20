package com.acme.weather.viewmodel

import com.acme.weather.model.api.Weather

class WeatherItemViewModel(weather: Weather) {

    var bgColor = weather.weatherIcon.bgColorRestId
    var currentTemp = weather.current?.fahrenheit?.toString() ?: "0"
    var location = weather.location.locationName
    var showingFahrenheit = true

    var highTemp = weather.high?.fahrenheit ?: 0
    var lowTemp = weather.low?.fahrenheit ?: 0

    var weatherIconContentDesc = weather.weatherIcon.descriptions[0]
    var weatherIcon = weather.weatherIcon.icResId

    var forecast = weather.forecast

}