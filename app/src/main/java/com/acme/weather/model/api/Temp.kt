package com.acme.weather.model.api

import kotlin.math.roundToInt

data class Temp(val fahrenheit: Int, val celsius: Int) {

    companion object {

        fun fromCelsius(c: Int) : Temp {
            return Temp(fahrenheit = (c * 1.8 + 32).roundToInt(), celsius = c)
        }

        fun fromFahrenheit(f: Int) : Temp {
            return Temp(celsius =  ((f - 32) / 1.8).roundToInt(), fahrenheit = f)
        }

    }

}