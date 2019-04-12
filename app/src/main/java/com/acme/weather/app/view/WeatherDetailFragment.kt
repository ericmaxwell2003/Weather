package com.acme.weather.app.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.navArgs
import com.acme.weather.R
import com.acme.weather.WeatherApplication
import com.acme.weather.databinding.WeatherDetailFragmentBinding
import com.acme.weather.common.di.Injectable
import com.acme.weather.app.viewmodel.WeatherDetailViewModel
import com.acme.weather.app.viewmodel.WeatherItemViewModel
import com.acme.weather.security.view.SecureFragment
import timber.log.Timber
import javax.inject.Inject

class WeatherDetailFragment : SecureFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var notificationManager: NotificationManager

    private lateinit var binding: WeatherDetailFragmentBinding

    val args: WeatherDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.weather_detail_fragment,
                container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.simulate_notification_with_deeplink, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.simulate_deep_link -> {
                scheduleNotificationForCity()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val zipCode = args.zipCode
        val shouldShowFahrenheit = args.useFahrenheit

        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(WeatherDetailViewModel::class.java)

        if(viewModel.weatherViewModel == null) {
            viewModel.setZipCode(zipCode, shouldShowFahrenheit)
        }

        viewModel.weatherViewModel?.observe(this, Observer { weather: WeatherItemViewModel? ->
            if(weather != null) {
                binding.vm = weather
            }
        })

    }

    val CHANNEL_ID = "weather.acme.com.channel"

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ACME_WEATHER"
            val descriptionText = "Weather Information"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun scheduleNotificationForCity() {


        // If I weren't already here....
        val pendingIntent = navController.createDeepLink()
                .setGraph(R.navigation.weather_graph)
                .setDestination(R.id.weather_detail)
                .setArguments(arguments)
                .createPendingIntent()

        createNotificationChannel()

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setContentTitle("Weather Report")
                .setContentText("Weather now in ${args.zipCode}")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }

}
