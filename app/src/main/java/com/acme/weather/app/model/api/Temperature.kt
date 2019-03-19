package com.acme.weather.app.model.api

import kotlin.math.roundToInt

data class Temperature(val fahrenheit: Int, val celsius: Int) {

    companion object {

        fun fromCelsius(c: Int) : Temperature {
            return Temperature(fahrenheit = (c * 1.8 + 32).roundToInt(), celsius = c)
        }

        fun fromFahrenheit(f: Int) : Temperature {
            return Temperature(celsius =  ((f - 32) / 1.8).roundToInt(), fahrenheit = f)
        }

    }

}