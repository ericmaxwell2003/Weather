package com.acme.weather.model.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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

    var currentTemp: Int = 0
    var highTemp : Int = 0
    var lowTemp: Int = 0

}