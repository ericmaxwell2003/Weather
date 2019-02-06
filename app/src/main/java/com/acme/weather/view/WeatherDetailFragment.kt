package com.acme.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.acme.weather.R
import com.acme.weather.WeatherApplication
import com.acme.weather.databinding.WeatherDetailFragmentBinding
import com.acme.weather.di.Injectable
import com.acme.weather.viewmodel.WeatherDetailViewModel
import com.acme.weather.viewmodel.WeatherItemViewModel
import timber.log.Timber
import javax.inject.Inject

class WeatherDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: WeatherDetailFragmentBinding

    val args: WeatherDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.weather_detail_fragment,
                container, false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toggle_units -> {
                Timber.d("Toggle units")
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val weatherId = args.weatherItemIdKey
        val shouldShowFahrenheit = args.useFahrenheitKey

        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(WeatherDetailViewModel::class.java)

        if(viewModel.weatherViewModel == null) {
            viewModel.setWeatherId(weatherId, shouldShowFahrenheit)
        }

        viewModel.weatherViewModel?.observe(this, Observer { weather: WeatherItemViewModel? ->
            if(weather != null) {
                binding.vm = weather
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity?.application as? WeatherApplication)?.refWatcher?.watch(this)
    }

}
