package com.acme.weather.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.acme.weather.R
import com.acme.weather.model.api.Weather
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = WeatherListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, "recipeList").commit()
        }
    }

    fun show(weatherId: Long) {

        val weatherFragment = WeatherDetailFragment.forWeatherSummary(weatherId)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("weather")
                .replace(R.id.fragment_container,
                        weatherFragment, null).commit()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

