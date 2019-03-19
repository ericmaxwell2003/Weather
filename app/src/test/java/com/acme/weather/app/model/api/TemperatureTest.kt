package com.acme.weather.app.model.api

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Due to rounding, calculations may not be symmetric.
 * e.g. 70 -> 21, might not mean 21 -> 70, could be 71 or 69.
 * That's okay for our simple weather app.
 */
class TemperatureTest {

    @Test
    fun testCreateTemperatureFromFahrenheit() {
        val expectedTemp = Temperature(fahrenheit = 70,celsius = 21)
        val actualTemp = Temperature.fromFahrenheit(70)
        assertEquals(expectedTemp, actualTemp)
    }

    @Test
    fun testCreateTemperatureFromCelsius() {
        val expectedTemp = Temperature(fahrenheit = 50,celsius = 10)
        val actualTemp = Temperature.fromCelsius(10)
        assertEquals(expectedTemp, actualTemp)
    }

}