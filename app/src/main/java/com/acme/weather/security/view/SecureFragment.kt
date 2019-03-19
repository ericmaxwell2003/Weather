package com.acme.weather.security.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.acme.weather.WeatherGraphDirections
import com.acme.weather.security.model.AuthenticationObject

abstract class SecureFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AuthenticationObject.isAuthenticated.observe(viewLifecycleOwner, Observer { isAuthenticated ->
            if(!isAuthenticated) {
                findNavController().navigate(WeatherGraphDirections.actionGlobalLogin())
            }
        })

    }

}