package com.acme.weather.app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acme.weather.R
import com.acme.weather.databinding.WeatherItemBinding
import com.acme.weather.app.model.api.Weather
import com.acme.weather.app.viewmodel.WeatherItemViewModel

class WeatherRecyclerAdapter(
        private val onItemClick: (zipCode: String) -> Unit,
        private val onItemLongClick: (id: Long) -> Unit)
    : RecyclerView.Adapter<WeatherRecyclerAdapter.RecipeViewHolder>() {

    private var showFahrenheit = true
    private var weatherList = emptyList<Weather>()

    fun setUnitOfTemperaturePreference(shouldShowFahrenheit: Boolean) {
        this.showFahrenheit = shouldShowFahrenheit
        notifyDataSetChanged()
    }

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
        val weatherItemVm = WeatherItemViewModel(weatherList[position], showFahrenheit)
        holder.binding.weatherItem.apply {
            setOnClickListener {
                onItemClick(weatherItem.location.zip)
            }
            setOnLongClickListener {
                if(weatherItem.id != null) {
                    onItemLongClick(weatherItem.id)
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
