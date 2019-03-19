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
import com.acme.weather.security.model.CredentialsViewModel


class EnterUsername() : Fragment() {

    val credentialsViewModel by activityViewModels<CredentialsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_enter_username, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        credentialsViewModel.clear()

        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.username_edit_text).text.toString()
            credentialsViewModel.username = username
            //findNavController().navigate(..)
        }

    }
}
