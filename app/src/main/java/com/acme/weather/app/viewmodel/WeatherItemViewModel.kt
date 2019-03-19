package com.acme.weather.app.viewmodel

import com.acme.weather.app.model.api.Weather

/**
 * Simplified Value Model object to display weatherList.
 */
class WeatherItemViewModel(weather: Weather, val showFahrenheit: Boolean)  {

    val bgColor = weather.forecastData?.weatherIcon?.backgroundColorResourceId ?: 0
    val location = weather.location.locationName
    val weatherIconContentDesc = weather.forecastData?.weatherIcon?.description
    val weatherIcon = weather.forecastData?.weatherIcon?.iconResourceId ?: 0
    val forecast = weather.forecastData?.forecast

    val currentTemp = weather.forecastData?.current?.run { if(showFahrenheit) fahrenheit else celsius }
    val highTemp = weather.forecastData?.high?.run { if(showFahrenheit) fahrenheit else celsius }
    val lowTemp = weather.forecastData?.low?.run { if(showFahrenheit) fahrenheit else celsius }

 }