package com.acme.weather.security.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.acme.weather.R
import com.acme.weather.common.navigation.isStartDestination
import com.acme.weather.common.navigation.parentDestination
import com.acme.weather.security.viewmodel.AuthenticationViewModel
import com.acme.weather.security.viewmodel.AuthenticationViewModel.AuthenticationStatus.AUTHENTICATED
import com.acme.weather.security.viewmodel.AuthenticationViewModel.AuthenticationStatus.USER_DECLINED


class EnterCredentials : Fragment() {

    val authenticationViewModel by activityViewModels<AuthenticationViewModel>()

    val usernameEditText by lazy { requireView().findViewById<EditText>(R.id.username_edit_text) }
    val passwordEditText by lazy { requireView().findViewById<EditText>(R.id.password_edit_text) }

    val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.enter_credentials, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            authenticationViewModel.authenticate(username, password)
        }

        authenticationViewModel.authenticationStatus.observe(viewLifecycleOwner, Observer { authState ->
            when(authState) {
                AUTHENTICATED -> navController.navigate(EnterCredentialsDirections.actionLoginPop())
                USER_DECLINED -> navController.popBackStack()
                else -> Unit
            }
        })


        requireActivity().addOnBackPressedCallback(viewLifecycleOwner, OnBackPressedCallback {
            authenticationViewModel.declineAuthentication()
            true
        })
    }
}


