package com.acme.weather.model.repository.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.acme.weather.model.repository.database.entity.WeatherEntity
import io.reactivex.Flowable


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun findAll(): LiveData<List<WeatherEntity>>

    @Query("SELECT * FROM weather")
    fun findAllSync(): List<WeatherEntity>

    @Query("SELECT * FROM weather where id = (:id)")
    fun byId(id: Long): LiveData<WeatherEntity>

    @Query("SELECT * FROM weather where id = (:id)")
    fun byIdSync(id: Long): WeatherEntity

    @Query("SELECT * FROM weather where zip = (:zip)")
    fun byZip(zip: String): LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(weatherEntity: WeatherEntity) : Long

    @Delete
    fun delete(weatherEntity: WeatherEntity)

}