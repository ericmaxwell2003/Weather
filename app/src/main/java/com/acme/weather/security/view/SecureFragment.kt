package com.acme.weather.security.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.acme.weather.WeatherGraphDirections
import com.acme.weather.security.viewmodel.AuthenticationViewModel
import com.acme.weather.security.viewmodel.AuthenticationViewModel.AuthenticationStatus.UNAUTHENTICATED
import com.acme.weather.security.viewmodel.AuthenticationViewModel.AuthenticationStatus.USER_DECLINED

abstract class SecureFragment : Fragment() {

    val authenticationViewModel by activityViewModels<AuthenticationViewModel>()
    val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationViewModel.authenticationStatus.observe(viewLifecycleOwner, Observer { authStatus ->
            when(authStatus) {
                UNAUTHENTICATED -> navController.navigate(WeatherGraphDirections.actionGlobalLogin())
                USER_DECLINED -> popBackStackOrExit()
                else -> Unit
            }
        })
    }

    fun logout() {
        authenticationViewModel.logout()
    }

    private fun popBackStackOrExit() {
        if(!navController.popBackStack()) {
            requireActivity().setVisible(false)
            requireActivity().finish()
        }
    }
}

