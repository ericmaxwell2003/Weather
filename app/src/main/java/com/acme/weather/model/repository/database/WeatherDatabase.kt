package com.acme.weather.model.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.acme.weather.model.repository.database.dao.WeatherDao
import com.acme.weather.model.repository.database.entity.Weather


@Database(entities = arrayOf(Weather::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        fun create(context: Context, useInMemory : Boolean): WeatherDatabase {

            return if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()
            } else {
                Room.databaseBuilder(context, WeatherDatabase::class.java, "weather.db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }
    }


}