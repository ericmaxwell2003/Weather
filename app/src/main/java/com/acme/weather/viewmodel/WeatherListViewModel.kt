package com.acme.weather.viewmodel

import android.arch.lifecycle.ViewModel
import com.acme.weather.model.repository.WeatherRespository

class WeatherListViewModel(private val weatherRespository: WeatherRespository) : ViewModel() {

    val weatherSummaries = weatherRespository.getWeatherSummaryList()



}
