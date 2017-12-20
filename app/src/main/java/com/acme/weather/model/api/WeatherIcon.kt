package com.acme.weather.model.api

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import com.acme.weather.R
import java.util.*

enum class WeatherIcon(val descriptions: List<String>,
                       @DrawableRes val icResId: Int, @ColorRes val bgColorRestId: Int) {

    SUNNY(listOf("clear-day", "clear-night"),
            R.mipmap.ic_sunny, R.color.bg_sunny),

    SNOWY(listOf("snow", "sleet"),
            R.mipmap.ic_snowy, R.color.bg_snowy),

    RAINY(listOf("rain"),
            R.mipmap.ic_rainy, R.color.bg_rainy),

    THUNDERSTORM(listOf("hail", "thunderstorm", "tornado"),
            R.mipmap.ic_thunderstorm, R.color.bg_thunderstorm),

    CLOUDY(listOf("wind", "fog", "cloudy"),
            R.mipmap.ic_cloudy, R.color.bg_cloudy),

    PARTLY_CLOUDY(listOf("partly-cloudy-day", "partly-cloudy-night"),
            R.mipmap.ic_partlycloudy, R.color.bg_partlycloudy);

    companion object {

        /**
         * Find proper WeatherIcon for description, or default to SUNNY.
         */
        fun fromDescription(description: String?) : WeatherIcon {

            return EnumSet
                    .allOf(WeatherIcon::class.java)
                    .find { it.descriptions.contains(description) } ?: SUNNY
        }
    }
}