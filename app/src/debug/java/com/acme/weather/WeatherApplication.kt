package com.acme.weather

import timber.log.Timber

class WeatherApplication : BaseWeatherApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
    }

}