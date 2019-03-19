package com.acme.weather.common.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.acme.weather.app.viewmodel.WeatherDetailViewModel;
import com.acme.weather.app.viewmodel.WeatherListViewModel;
import com.acme.weather.app.viewmodel.WeatherViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherListViewModel.class)
    abstract ViewModel bindWeatherListViewModel(WeatherListViewModel weatherListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailViewModel.class)
    abstract ViewModel bindWeatherDetailViewModel(WeatherDetailViewModel weatherDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(
            WeatherViewModelFactory factory);
}
