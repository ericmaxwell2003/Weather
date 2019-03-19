package com.acme.weather.security.model

import androidx.lifecycle.ViewModel

class AuthenticationViewModel() : ViewModel() {

    var isAuthenticated = false

    fun authenticate(username: String, password: String, onAuthenticated: (() -> Unit)) {
        isAuthenticated = true
        onAuthenticated()
    }

}
