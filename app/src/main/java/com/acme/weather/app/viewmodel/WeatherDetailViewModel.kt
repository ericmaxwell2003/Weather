package com.acme.weather.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.acme.weather.app.model.repository.WeatherRepository
import javax.inject.Inject

class WeatherDetailViewModel @Inject constructor(
        private val weatherRepository: WeatherRepository) : ViewModel() {

    var weatherViewModel: LiveData<WeatherItemViewModel>? = null

    /**
     * Set the id of the weatherList to show and whether to show celsius or fahrenheit.
     * Ideally the preference on which unit to display should come from
     * some sort of persistent setting, but this is just a simple example.
     */
    fun setWeatherId(id: Long, shouldShowFahrenheit: Boolean) {
        weatherViewModel = Transformations.map(weatherRepository.byIdentifier(id)) {
            WeatherItemViewModel(weather = it, showFahrenheit = shouldShowFahrenheit)
        }
    }

    fun setZipCode(zipCode: String, shouldShowFahrenheit: Boolean) {
        weatherViewModel = Transformations.map(weatherRepository.byZipCode(zipCode)) {
            WeatherItemViewModel(weather = it, showFahrenheit = shouldShowFahrenheit)
        }
    }

}
