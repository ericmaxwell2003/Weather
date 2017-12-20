package com.acme.weather.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.databinding.WeatherItemBinding
import com.acme.weather.model.api.Weather
import com.acme.weather.viewmodel.WeatherItemViewModel
import com.acme.weather.viewmodel.WeatherListViewModel
import org.jetbrains.anko.coroutines.experimental.asReference
import java.lang.ref.WeakReference

class WeatherRecyclerAdapter(weatherListViewModel: WeatherListViewModel)
    : RecyclerView.Adapter<WeatherRecyclerAdapter.RecipeViewHolder>() {

    private val weakWeatherListViewModel = WeakReference(weatherListViewModel)
    private var weatherList = emptyList<Weather>()

    fun setWeatherList(weatherList: List<Weather>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = DataBindingUtil
                .inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),
                                            R.layout.weather_item,
                                            parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        val weatherItemVm = WeatherItemViewModel(weatherList[position])
        holder.binding.weatherItem.apply {

            setOnClickListener {
                if(weatherItem.id != null) {
                    weakWeatherListViewModel.get()?.onLocationItemSelected(weatherItem.id)
                }
            }
            setOnLongClickListener {
                if(weatherItem.id != null) {
                    weakWeatherListViewModel.get()?.onLocationDeleted(weatherItem.id)
                }
                true
            }
        }

        holder.binding.vm = weatherItemVm
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = weatherList.size

    class RecipeViewHolder(val binding: WeatherItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}
