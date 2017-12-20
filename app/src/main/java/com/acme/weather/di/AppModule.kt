package com.acme.weather.di

import android.app.Application
import android.location.Geocoder
import com.acme.weather.model.WeatherLocationService
import com.acme.weather.model.api.Weather
import com.acme.weather.model.repository.WeatherRepository
import com.acme.weather.model.repository.database.WeatherDatabase
import com.acme.weather.model.repository.database.dao.WeatherDao
import com.acme.weather.model.repository.network.WeatherApi
import dagger.Module
import dagger.Provides
import java.security.AccessControlContext
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class, NetworkModule::class))
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): WeatherDatabase {
        return WeatherDatabase.create(app, false)
    }

    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDatabase): WeatherDao {
        return db.weatherDao()
    }

    @Singleton
    @Provides
    fun weatherRepository(db: WeatherDatabase, api: WeatherApi): WeatherRepository {
        return WeatherRepository(db, api)
    }

    @Singleton
    @Provides
    fun weatherLocationService(geocoder: Geocoder) : WeatherLocationService {
        return WeatherLocationService(geocoder)
    }

    @Singleton
    @Provides
    fun geoCoder(context: Application) : Geocoder {
        return Geocoder(context)
    }

}