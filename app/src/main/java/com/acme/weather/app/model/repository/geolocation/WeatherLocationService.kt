package com.acme.weather.app.model.repository.geolocation

import android.location.Geocoder
import com.acme.weather.app.model.api.Location
import javax.inject.Inject

class WeatherLocationService @Inject constructor(val geoCoder: Geocoder) {

    fun locationForZip(zip: String) : Location? {
        val address =  geoCoder.getFromLocationName(zip, 1).firstOrNull()
        return if(address == null) {
            null
        } else {
            Location(locationName = address.locality,
                    zip = address.postalCode,
                    longitude = address.longitude.toString(),
                    latitude = address.latitude.toString())
        }

    }

}