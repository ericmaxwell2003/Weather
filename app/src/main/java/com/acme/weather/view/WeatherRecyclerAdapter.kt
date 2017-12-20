package com.acme.weather.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.databinding.WeatherItemBinding
import com.acme.weather.model.api.Weather
import com.acme.weather.viewmodel.WeatherItemViewModel

class WeatherRecyclerAdapter(
        private val onItemClick: (id: Long) -> Unit,
        private val onItemLongClick: (id: Long) -> Unit)
    : RecyclerView.Adapter<WeatherRecyclerAdapter.RecipeViewHolder>() {

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
        val weatherItemVm = WeatherItemViewModel(weatherList[position], true)
        holder.binding.weatherItem.apply {
            setOnClickListener {
                if(weatherItem.id != null) {
                    onItemClick(weatherItem.id)
                }
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
