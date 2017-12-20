package com.acme.weather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acme.weather.R
import com.acme.weather.WeatherApplication
import com.acme.weather.databinding.WeatherDetailFragmentBinding
import com.acme.weather.di.Injectable
import com.acme.weather.viewmodel.WeatherDetailViewModel
import javax.inject.Inject

class WeatherDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

        val weatherId = arguments?.getLong(WEATHER_ITEM_ID_KEY)

        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(WeatherDetailViewModel::class.java)

        if(weatherId != null && viewModel.weather == null) {
            viewModel.setWeatherId(weatherId)
        }

        viewModel.weather?.observe(this, Observer { weather ->
            if(weather != null) {
                binding.vm = weather
            }
        })

    }

    companion object {

        private val WEATHER_ITEM_ID_KEY = "weather_id"

        fun forWeatherSummary(weatherId: Long): WeatherDetailFragment {
            val fragment = WeatherDetailFragment()
            val args = Bundle()
            args.putLong(WEATHER_ITEM_ID_KEY, weatherId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity?.application as? WeatherApplication)?.refWatcher?.watch(this)
    }

}
