package com.acme.weather.security.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object AuthenticationObject : ViewModel() {

    val isAuthenticated = MutableLiveData<Boolean>()

    init {
        isAuthenticated.value = false
    }

    fun authenticate(username: String, password: String) {
        // disregard credentials, just set auth status to true
        isAuthenticated.value = true
    }

}
