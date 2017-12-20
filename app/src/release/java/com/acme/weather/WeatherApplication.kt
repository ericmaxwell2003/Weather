package com.acme.weather

import android.util.Log
import com.acme.weather.di.AppInjector
import timber.log.Timber

class WeatherApplication : BaseWeatherApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(ReleaseTree())
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}


