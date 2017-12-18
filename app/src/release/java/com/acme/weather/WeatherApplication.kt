package com.acme.weather

import android.util.Log
import timber.log.Timber

class WeatherApplication : BaseWeatherApplication() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(ReleaseTree())
    }

}


