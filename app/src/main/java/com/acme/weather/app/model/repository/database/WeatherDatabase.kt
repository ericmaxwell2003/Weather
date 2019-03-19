package com.acme.weather.app.model.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.acme.weather.app.model.repository.database.dao.WeatherDao
import com.acme.weather.app.model.repository.database.entity.WeatherEntity


@Database(entities = arrayOf(WeatherEntity::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        fun create(context: Context, useInMemory : Boolean): WeatherDatabase {

            return if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()
            } else {
                Room.databaseBuilder(context, WeatherDatabase::class.java, "weatherList.db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }
    }


}