package com.acme.weather.model.repository.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Weather {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0

    var latitude: Long = 0
    var longitude: Long = 0

    var zip: String = ""

    var forecast: String? = null
    var locationName: String? = null

    @Embedded(prefix = "current_")
    var currentTemp: Temperature? = null

    @Embedded(prefix = "high_")
    var highTemp : Temperature? = null

    @Embedded(prefix = "low_")
    var lowTemp: Temperature? = null

}