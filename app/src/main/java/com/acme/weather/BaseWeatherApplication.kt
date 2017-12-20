package com.acme.weather

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

abstract class BaseWeatherApplication : Application() {

    var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        this.refWatcher = LeakCanary.install(this)
    }

}
