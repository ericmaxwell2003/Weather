package com.acme.weather.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acme.weather.app.model.repository.WeatherRepository
import com.acme.weather.app.model.repository.geolocation.WeatherLocationService
import org.jetbrains.anko.doAsync
import timber.log.Timber
import javax.inject.Inject

sealed class State
class DEFAULT : State()
class LOCATION_ADD_PENDING : State()
class LOCATION_ADD_FAILED(val error: String) : State()

class WeatherListViewModel @Inject constructor(
        val weatherRepository: WeatherRepository,
        val weatherLocationService: WeatherLocationService) : ViewModel() {

    val weatherList = weatherRepository.weatherList

    val shouldPreferFahrenheit = MutableLiveData<Boolean>().apply { value = true }
    val state = MutableLiveData<State>().apply{ value = DEFAULT() }

    fun onLocationEntered(zip: String) {
        Timber.i("onLocationEntered: ${zip}")
        state.value = LOCATION_ADD_PENDING()
        doAsync {
            val location = weatherLocationService.locationForZip(zip)
            if(location != null) {
                weatherRepository.addWeatherLocation(location = location)
                state.postValue(DEFAULT()) // post, automatically emits to UI thread.
            } else {
                state.postValue(LOCATION_ADD_FAILED("Location not found"))
                state.postValue(DEFAULT())
            }
        }
    }

    fun onLocationDeleted(id: Long) {
        weatherRepository.removeWeatherLocation(id)
    }

    fun toggleUnitOfMeasurement() {
        val currentState: Boolean = shouldPreferFahrenheit.value ?: true
        shouldPreferFahrenheit.value = !currentState
    }
}

