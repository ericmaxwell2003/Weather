package com.acme.weather.view

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.acme.weather.R
import com.acme.weather.WeatherApplication
import com.acme.weather.databinding.WeatherListFragmentBinding
import com.acme.weather.di.Injectable
import com.acme.weather.viewmodel.DEFAULT
import com.acme.weather.viewmodel.LOCATION_ADD_FAILED
import com.acme.weather.viewmodel.LOCATION_ADD_PENDING
import com.acme.weather.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject


class WeatherListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: WeatherListFragmentBinding
    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter
    private lateinit var weatherListViewModel: WeatherListViewModel

    val REQUEST_LOCATION = 0
    val DIALOG_LOCATION = "DialogLocation"

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

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.weather_list_fragment, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        val directions = WeatherListFragmentDirections.navigateToWeatherDetails(id)
                        .setUseFahrenheitKey(true)
        findNavController().navigate(directions)

    }

//    fun showZipDialog() {
//        hideProgressDialog()
//
//        val directions = WeatherListFragmentDirections.navigateToAddLocationDialog()
//        findNavController().navigate(directions)
//    }

    fun showZipDialog() {
        hideProgressDialog()
        val fm = fragmentManager
        fm?.let { fragmentMgr ->
            val locationDialog = LocationDialogFragment()
            locationDialog.setTargetFragment(this, REQUEST_LOCATION)
            locationDialog.show(fragmentMgr, DIALOG_LOCATION)
        }
    }


//    override fun onNavigationResult(result: Bundle) {
//        result.getString(LocationDialogFragment.EXTRA_LOCATION)?.let { zip ->
//            weatherListViewModel.onLocationEntered(zip)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_LOCATION) {

            when(resultCode) {
                RESULT_OK -> data?.getStringExtra(LocationDialogFragment.EXTRA_LOCATION)?.let { zip ->
                    weatherListViewModel.onLocationEntered(zip)
                }
                RESULT_CANCELED -> Timber.i("User cancelled add location")
                else -> Timber.e("Unknown resquestCode [${requestCode}]")
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity?.application as? WeatherApplication)?.refWatcher?.watch(this)
    }

}
