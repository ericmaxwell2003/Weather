package com.acme.weather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.acme.weather.model.WeatherLocationService
import com.acme.weather.model.repository.WeatherRepository
import org.jetbrains.anko.doAsync
import timber.log.Timber
import javax.inject.Inject

sealed class State
class DEFAULT : State()
class LOCATION_ADD_PROMPT : State()
class LOCATION_ADD_PENDING : State()
class LOCATION_ADD_FAILED(val error: String) : State()
class LOCATION_VIEW_DETAIL_PENDING(val id: Long) : State()

class WeatherListViewModel @Inject constructor(
        val weatherRepository: WeatherRepository, val weatherLocationService: WeatherLocationService)
    : ViewModel() {

    val weatherSummaries = weatherRepository.weatherList
    val state = MutableLiveData<State>().apply{ value = DEFAULT() }

    fun onAddLocationClicked() {
        Timber.i("onAddLocationClicked")
        state.value = LOCATION_ADD_PROMPT()
    }

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
            }
        }
    }

    fun onLocationItemSelected(id: Long) {
        state.value = LOCATION_VIEW_DETAIL_PENDING(id)
    }

    fun onLocationViewDetail() {
        state.value = DEFAULT()
    }

}

