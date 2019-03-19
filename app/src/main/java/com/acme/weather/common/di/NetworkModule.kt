package com.acme.weather.common.di

import android.app.Application
import com.acme.weather.app.model.repository.network.WeatherApi
import com.acme.weather.app.model.repository.network.WeatherForecastService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
open class NetworkModule {

    @Provides
    internal fun retrofitBuilder(okHttpClientBuilder: OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("${BASE_URL}/${API_KEY}")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientBuilder.build())
    }

    @Provides
    internal open fun okHttpClientBuilder(application: Application): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    internal fun retrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit {
        return retrofitBuilder(okHttpClientBuilder).build()
    }

    @Provides
    internal fun weatherForecastService(weatherApi: WeatherApi) : WeatherForecastService {
        return WeatherForecastService(weatherApi)
    }

    @Provides
    internal fun weatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    companion object {
        val BASE_URL = "https://api.darksky.net/forecast"
        val API_KEY = "56e3245918103f95ef6f2e5cc9c4063b/"
    }
}