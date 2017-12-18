package com.acme.weather.com.acme.weather.model.repository.database

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.acme.weather.model.repository.database.WeatherDatabase
import com.acme.weather.model.repository.database.dao.WeatherDao
import com.acme.weather.model.repository.database.entity.Temperature
import com.acme.weather.model.repository.database.entity.Weather
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class WeatherCrudTests {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: WeatherDatabase

    @get:Rule
    val instantExecuteRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = WeatherDatabase.create(context, true)
        weatherDao = db.weatherDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testCreateAndRetrieveWeather() {
        val weather = createWeather()
        val pk = weatherDao.insertOrUpdate(weather)
        val byPk = weatherDao.byId(pk)
        byPk.observeForever {
            assertThat(it?.zip, equalTo(weather.zip))
            assertThat(it?.latitude, equalTo(weather.latitude))
            assertThat(it?.longitude, equalTo(weather.longitude))
            assertThat(it?.forecast, equalTo(weather.forecast))
            assertThat(it?.locationName, equalTo(weather.locationName))
            assertThat(it?.currentTemp?.celsius, equalTo(weather.currentTemp?.celsius))
            assertThat(it?.currentTemp?.fahrenheit, equalTo(weather.currentTemp?.fahrenheit))
            assertThat(it?.highTemp?.celsius, equalTo(weather.highTemp?.celsius))
            assertThat(it?.highTemp?.fahrenheit, equalTo(weather.highTemp?.fahrenheit))
            assertThat(it?.lowTemp?.celsius, equalTo(weather.lowTemp?.celsius))
            assertThat(it?.lowTemp?.fahrenheit, equalTo(weather.lowTemp?.fahrenheit))
        }
    }

    private fun createWeather() = Weather().apply {
        latitude = 13
        longitude = 52
        zip = "90706"
        forecast = "Sunny with a chance of rain."
        locationName = "Bellflower, CA"
        currentTemp = createTemperature()
        highTemp = createTemperature()
        lowTemp = createTemperature()
    }

    private fun createTemperature() = Temperature().apply {
        fahrenheit = 212
        celsius = 100
    }
}