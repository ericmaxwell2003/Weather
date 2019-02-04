package com.acme.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.acme.weather.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//
//            val fragment = WeatherListFragment()
//
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.fragment_container, fragment, "recipeList").commit()
//
//        }
    }

//    fun show(weatherId: Long, displayAsFahrenheit: Boolean) {
//
//        val weatherFragment = WeatherDetailFragment
//                .forWeatherSummary(weatherId, displayAsFahrenheit)
//
//        supportFragmentManager
//                .beginTransaction()
//                .addToBackStack("weatherList")
//                .replace(R.id.fragment_container,
//                        weatherFragment, null).commit()
//    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

