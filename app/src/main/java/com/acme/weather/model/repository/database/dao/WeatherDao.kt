package com.acme.weather.model.repository.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.acme.weather.model.repository.database.entity.Weather


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun findAll(): LiveData<List<Weather>>

    @Query("SELECT * FROM weather where id = (:id)")
    fun byId(id: Long): LiveData<Weather>

    @Query("SELECT * FROM weather where zip = (:zip)")
    fun byZip(zip: String): LiveData<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(weather: Weather) : Long

    @Delete
    fun delete(weather: Weather)

}