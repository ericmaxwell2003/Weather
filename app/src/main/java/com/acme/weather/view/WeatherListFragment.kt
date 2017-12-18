package com.acme.weather.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.model.api.WeatherSummary
import com.acme.weather.viewmodel.WeatherListViewModel

class WeatherListFragment : Fragment() {

    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter
    private lateinit var weatherListViewModel: WeatherListViewModel

    private val recipeClickListener = ItemClickListener<WeatherSummary> { _ ->
        //(activity as? MainActivity)?.show(recipe)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = LayoutInflater
                .from(context)
                .inflate(R.layout.weather_list_fragment, container, false)

        weatherRecyclerAdapter = WeatherRecyclerAdapter(recipeClickListener)
        val recyclerView = root.findViewById<RecyclerView>(R.id.weather_list_recycler_view)
        recyclerView.adapter = weatherRecyclerAdapter
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherListViewModel = ViewModelProviders
                .of(this)
                .get(WeatherListViewModel::class.java)

//        weatherListViewModel.recipes.observe(this, Observer { recipes ->
//            if(recipes != null) {
//                weatherRecyclerAdapter.setRecipeList(recipes)
//            }
//        })
    }
}
