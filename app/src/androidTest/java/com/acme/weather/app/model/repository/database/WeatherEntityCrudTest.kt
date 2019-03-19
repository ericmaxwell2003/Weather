package com.acme.weather.app.model.repository.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.acme.weather.app.model.repository.database.dao.WeatherDao
import com.acme.weather.app.model.repository.database.entity.TemperatureEntity
import com.acme.weather.app.model.repository.database.entity.WeatherEntity
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class WeatherEntityCrudTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: WeatherDatabase

    @get:Rule
    val instantExecuteRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
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

    @Test
    fun testFindByZip() {
        val springfieldWeather = createWeather().apply { zip = "45503" }
        val columbusWeather = createWeather().apply { zip = "43231" }

        weatherDao.insertOrUpdate(springfieldWeather)
        weatherDao.insertOrUpdate(columbusWeather)

        val byZip = weatherDao.byZip("45503")
        byZip.observeForever {
            assertThat(it?.zip, equalTo("45503"))
        }
    }

    @Test
    fun testFindAll() {
        val springfieldWeather = createWeather().apply { zip = "45503" }
        val columbusWeather = createWeather().apply { zip = "43231" }

        weatherDao.insertOrUpdate(springfieldWeather)
        weatherDao.insertOrUpdate(columbusWeather)

        val all = weatherDao.findAll()
        all.observeForever {}
        assertThat(all.value?.count(), equalTo(2))
    }

    @Test
    fun testDelete() {
        val weather = createWeather()
        val id = weatherDao.insertOrUpdate(weather)
        weather.id = id

        val all = weatherDao.findAll()
        all.observeForever {}

        // Assert 1 entry
        assertThat(all.value?.count(), equalTo(1))

        // Delete it
        weatherDao.delete(weather)

        // Assert 0 entries
        assertThat(all.value?.count(), equalTo(0))
    }


    private fun createWeather() = WeatherEntity().apply {
        latitude = "13"
        longitude = "52"
        zip = "90706"
        forecast = "Sunny with perfect temperatures and never a chance of rain."
        locationName = "Bellflower, CA"
        currentTemp = createTemperature()
        highTemp = createTemperature()
        lowTemp = createTemperature()
    }

    private fun createTemperature() = TemperatureEntity().apply {
        fahrenheit = 212
        celsius = 100
    }
}