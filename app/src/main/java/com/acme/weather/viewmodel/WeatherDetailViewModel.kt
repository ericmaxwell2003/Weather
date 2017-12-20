package com.acme.weather.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.acme.weather.model.repository.WeatherRepository
import javax.inject.Inject

class WeatherDetailViewModel @Inject constructor(
        private val weatherRepository: WeatherRepository) : ViewModel() {

    var weatherViewModel: LiveData<WeatherItemViewModel>? = null

    /**
     * Set the id of the weather to show and whether to show celsius or fahrenheit.
     * Ideally the preference on which unit to display should come from
     * some sort of persistent setting, but this is just a simple example.
     */
    fun setWeatherId(id: Long, shouldShowFahrenheit: Boolean) {
        weatherViewModel = Transformations.map(weatherRepository.byIdentifier(id), {
            WeatherItemViewModel(weather = it, showFahrenheit = shouldShowFahrenheit)
        })
    }

}
