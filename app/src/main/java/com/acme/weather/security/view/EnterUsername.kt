package com.acme.weather.security.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acme.weather.R
import com.acme.weather.security.viewmodel.CredentialsViewModel


class EnterUsername : Fragment() {

    val credentialsViewModel by activityViewModels<CredentialsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_enter_username, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.username_edit_text).text.toString()
            credentialsViewModel.username = username
            findNavController().navigate(EnterUsernameDirections.navigateToEnterPassword())
        }

    }
}
