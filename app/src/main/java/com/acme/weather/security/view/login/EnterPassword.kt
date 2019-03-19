package com.acme.weather.app.view.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.acme.weather.R
import com.acme.weather.security.model.AuthenticationViewModel
import com.acme.weather.security.model.CredentialsViewModel

class EnterPassword() : Fragment() {

    val credentialsViewModel by activityViewModels<CredentialsViewModel>()
    val authenticationViewModel by activityViewModels<AuthenticationViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_enter_password, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            val username = credentialsViewModel.username
            val password = view.findViewById<EditText>(R.id.username_edit_text).text.toString()

            authenticationViewModel.authenticate(username, password) {
//                findNavController().popBackStack()
            }
        }

    }
}
