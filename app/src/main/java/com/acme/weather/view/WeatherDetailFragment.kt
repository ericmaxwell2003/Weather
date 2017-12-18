package com.acme.weather.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.databinding.WeatherDetailFragmentBinding
import com.acme.weather.model.api.WeatherSummary
import com.acme.weather.viewmodel.WeatherDetailViewModel

class WeatherDetailFragment : Fragment() {

    private lateinit var binding: WeatherDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.weather_detail_fragment,
                container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val factory = WeatherDetailViewModel.Factory(arguments?.getString(RECIPE_ID_KEY) ?: "0")

        val viewModel = ViewModelProviders
                .of(this /*, factory*/)
                .get(WeatherDetailViewModel::class.java)

//        viewModel.recipe.observe(this, Observer { recipe ->
//            binding.recipe = recipe
//            val ingredients = (recipe as? RecipeEntity)?.ingredients
//            if (ingredients != null) {
//                weatherRecyclerViewListAdapter.setIngredients(ingredients)
//            }
//        })

    }

    companion object {

        private val WEATHER_ITEM_ID_KEY = "recipe_id"

        fun forWeatherSummary(weatherSummary: WeatherSummary): WeatherDetailFragment {
            val fragment = WeatherDetailFragment()
            val args = Bundle()
            args.putInt(WEATHER_ITEM_ID_KEY, weatherSummary.id)
            fragment.arguments = args
            return fragment
        }
    }
}
