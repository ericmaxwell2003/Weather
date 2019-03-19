package com.acme.weather.app.model.repository.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var latitude: String = ""
    var longitude: String = ""
    var locationName: String = ""
    var zip: String = ""

    var iconDescription: String? = null
    var forecast: String? = null

    @Embedded(prefix = "current_")
    var currentTemp: TemperatureEntity = TemperatureEntity()

    @Embedded(prefix = "high_")
    var highTemp: TemperatureEntity = TemperatureEntity()

    @Embedded(prefix = "low_")
    var lowTemp: TemperatureEntity = TemperatureEntity()

}