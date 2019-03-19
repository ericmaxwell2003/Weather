package com.acme.weather.security.model

import androidx.lifecycle.ViewModel

class CredentialsViewModel(var username: String = "", var password: String = "") : ViewModel() {

    fun clear() {
        username = ""
        password = ""
    }

}
