package com.acme.weather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.*
import com.acme.weather.R
import com.acme.weather.WeatherApplication
import com.acme.weather.databinding.WeatherListFragmentBinding
import com.acme.weather.di.Injectable
import com.acme.weather.viewmodel.DEFAULT
import com.acme.weather.viewmodel.LOCATION_ADD_FAILED
import com.acme.weather.viewmodel.LOCATION_ADD_PENDING
import com.acme.weather.viewmodel.WeatherListViewModel
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.customView
import org.jetbrains.anko.editText
import org.jetbrains.anko.verticalLayout
import javax.inject.Inject


class WeatherListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: WeatherListFragmentBinding
    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter
    private lateinit var weatherListViewModel: WeatherListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater?.inflate(R.menu.weather_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toggle_units -> {
                weatherListViewModel.toggleUnitOfMeasurement()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<WeatherListFragmentBinding>(
                LayoutInflater.from(context),
                R.layout.weather_list_fragment, container, false)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherListViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(WeatherListViewModel::class.java)

        weatherRecyclerAdapter = WeatherRecyclerAdapter(
                   onItemClick = { id -> showDetail(id) },
                   onItemLongClick = { id -> weatherListViewModel.onLocationDeleted(id) })

        val recyclerView = binding.weatherListRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = weatherRecyclerAdapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        binding.vm = weatherListViewModel

        weatherListViewModel.weatherList.observe(this, Observer { weatherSummaryList ->
            if (weatherSummaryList != null) {
                weatherRecyclerAdapter.setWeatherList(weatherSummaryList)
            }
        })

        weatherListViewModel.shouldPreferFahrenheit.observe(this, Observer {
            weatherRecyclerAdapter.setUnitOfTemperaturePreference(shouldShowFahrenheit = it ?: true)
        })

        weatherListViewModel.state.observe(this, Observer { state ->
            when (state) {
                is DEFAULT -> hideProgressDialog()
                is LOCATION_ADD_PENDING -> showProgressDialog()
                is LOCATION_ADD_FAILED -> showError(state.error)
                null -> hideProgressDialog()
            }
        })

        binding.fabAddZip.setOnClickListener { showZipDialog() }
    }

    fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressDialog() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun showError(error: String) {
        hideProgressDialog()
        val v = view
        if(v != null) {
            Snackbar.make(v, error, Snackbar.LENGTH_SHORT)
        }
    }

    fun showDetail(id: Long) {
        (activity as MainActivity).show(id,
                weatherListViewModel.shouldPreferFahrenheit.value ?: true)
    }

    fun showZipDialog() {
        hideProgressDialog()
        context?.apply {
            alert {
                Appcompat
                customView {
                    verticalLayout {
                        val zip = editText {
                            hint = "ZIP Code"
                            inputType = android.text.InputType.TYPE_CLASS_NUMBER
                        }
                        positiveButton("Add") {
                            weatherListViewModel.onLocationEntered(zip.text.toString())
                        }
                        negativeButton("Cancel") {
                            timber.log.Timber.d("Location add cancelled")
                        }
                    }
                }
            }.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity?.application as? WeatherApplication)?.refWatcher?.watch(this)
    }

}
