package com.acme.weather.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.acme.weather.R
import com.acme.weather.model.api.WeatherSummary

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = WeatherListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, "recipeList").commit()
        }
    }

    fun show(weatherSummary: WeatherSummary) {

        val recipeFragment = WeatherDetailFragment.forWeatherSummary(weatherSummary)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("recipe")
                .replace(R.id.fragment_container,
                        recipeFragment, null).commit()
    }

}