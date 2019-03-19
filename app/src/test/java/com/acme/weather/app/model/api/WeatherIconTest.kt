package com.acme.weather.app.model.api

import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class WeatherIconTest {

    @Test
    fun testFromDescription_sunny_desc() {
        listOf("clear-day", "clear-night", null, "").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(Sunny::class.java))
        }
    }
    @Test
    fun testFromDescription_snowy_desc() {
        listOf("snow", "sleet").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(Snowy::class.java))
        }
    }
    @Test
    fun testFromDescription_rainy_desc() {
        listOf("rain").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(Rainy::class.java))
        }
    }
    @Test
    fun testFromDescription_cloudy_desc() {
        listOf("hail", "thunderstorm", "tornado").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(Thunderstorm::class.java))
        }
    }
    @Test
    fun testFromDescription_thunderstorm_desc() {
        listOf("wind", "fog", "cloudy").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(Cloudy::class.java))
        }
    }
    @Test
    fun testFromDescription_partlycloudy_desc() {
        listOf("partly-cloudy-day", "partly-cloudy-night").forEach {
            assertThat(WeatherIcon.fromDescription(it), IsInstanceOf(PartlyCloudy::class.java))
        }
    }

}