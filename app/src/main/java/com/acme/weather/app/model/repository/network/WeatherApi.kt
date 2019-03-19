package com.acme.weather.app.model.repository.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherApi {


    @GET("{latLong}?exclude=flags,alerts,hourly,minutely&units=us")
    fun getWeatherForCoordinate(@Path("latLong") latLong: String) : Single<WeatherApiResponse>

}