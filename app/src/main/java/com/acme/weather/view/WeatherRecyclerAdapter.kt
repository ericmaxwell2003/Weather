package com.acme.weather.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.databinding.WeatherItemBinding
import com.acme.weather.model.api.WeatherSummary

class WeatherRecyclerAdapter(private val clickListener: ItemClickListener<WeatherSummary>)
    : RecyclerView.Adapter<WeatherRecyclerAdapter.RecipeViewHolder>() {

    private var weatherSummaryList = emptyList<WeatherSummary>()

    fun setWeatherList(weatherSummaryList: List<WeatherSummary>) {
        this.weatherSummaryList = weatherSummaryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = DataBindingUtil
                .inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),
                                            R.layout.weather_item,
                                            parent, false)

        binding.itemClickListener = clickListener
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.weatherSummary = weatherSummaryList[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = weatherSummaryList.size

    class RecipeViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)
}
