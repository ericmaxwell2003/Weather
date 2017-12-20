package com.acme.weather.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.acme.weather.model.repository.WeatherRepository
import javax.inject.Inject

class WeatherDetailViewModel @Inject constructor(
        private val weatherRepository: WeatherRepository) : ViewModel() {

    var weather: LiveData<WeatherItemViewModel>? = null

    fun setWeatherId(id: Long) {
        weather = Transformations.map(weatherRepository.byIdentifier(id), {
            WeatherItemViewModel(it)
        })
    }

}
