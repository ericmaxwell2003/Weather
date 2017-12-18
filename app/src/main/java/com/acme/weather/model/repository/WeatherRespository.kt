package com.acme.weather.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.acme.weather.model.api.Temperature
import com.acme.weather.model.api.WeatherSummary

class WeatherRespository {

    fun getWeatherSummaryList() : LiveData<List<WeatherSummary>> {

        val results = MutableLiveData<List<WeatherSummary>>()
        results.value = listOf(
                WeatherSummary(id=1, zip = "90706",
                        current = Temperature.fromFahrenheit(72),
                        high = Temperature.fromFahrenheit(100),
                        low = Temperature.fromFahrenheit(29)),
                WeatherSummary(id=1, zip = "90706",
                        current = Temperature.fromFahrenheit(72),
                        high = Temperature.fromFahrenheit(100),
                        low = Temperature.fromFahrenheit(29)),
                WeatherSummary(id=1, zip = "90706",
                        current = Temperature.fromFahrenheit(72),
                        high = Temperature.fromFahrenheit(100),
                        low = Temperature.fromFahrenheit(29)),
                WeatherSummary(id=1, zip = "90706",
                        current = Temperature.fromFahrenheit(72),
                        high = Temperature.fromFahrenheit(100),
                        low = Temperature.fromFahrenheit(29))
        )
        return results

    }

}