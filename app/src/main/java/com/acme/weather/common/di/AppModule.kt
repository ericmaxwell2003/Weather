package com.acme.weather.common.di

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.location.Geocoder
import com.acme.weather.app.model.repository.WeatherRepository
import com.acme.weather.app.model.repository.database.WeatherDatabase
import com.acme.weather.app.model.repository.database.dao.WeatherDao
import com.acme.weather.app.model.repository.geolocation.WeatherLocationService
import com.acme.weather.app.model.repository.network.WeatherForecastService
import dagger.Module
import dagger.Provides
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
    fun weatherRepository(dao: WeatherDao, service: WeatherForecastService): WeatherRepository {
        return WeatherRepository(dao, service)
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

    @Singleton
    @Provides
    fun notificationManager(context: Application) : NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

}