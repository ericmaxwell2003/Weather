package com.acme.weather.app.model.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.acme.weather.app.model.repository.database.entity.WeatherEntity


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun findAll(): LiveData<List<WeatherEntity>>

    @Query("SELECT * FROM weather")
    fun findAllSync(): List<WeatherEntity>

    @Query("SELECT * FROM weather WHERE id = (:id)")
    fun byId(id: Long): LiveData<WeatherEntity>

    @Query("SELECT * FROM weather WHERE id = (:id)")
    fun byIdSync(id: Long): WeatherEntity

    @Query("SELECT * FROM weather WHERE zip = (:zip)")
    fun byZip(zip: String): LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(weatherEntity: WeatherEntity) : Long

    @Delete
    fun delete(weatherEntity: WeatherEntity)

}