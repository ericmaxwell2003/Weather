package com.acme.weather.security.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {

    enum class AuthenticationStatus {
        UNAUTHENTICATED, // user needs to authenticate state
        AUTHENTICATED, // user is in authenticated state
        USER_DECLINED, // user has declined to authenticate
    }

    val authenticationStatus = MutableLiveData<AuthenticationStatus>()

    init {
        logout()
    }

    /* Simulate Login with user/pass */
    fun authenticate(username: String, password: String) {
        // disregard credentials, just set auth status to true
        authenticationStatus.value = AuthenticationStatus.AUTHENTICATED
    }

    /* Simulate Logout */
    fun logout() {
        authenticationStatus.value = AuthenticationStatus.UNAUTHENTICATED
    }

    /* Simulate user declining to authenticate */
    fun declineAuthentication() {
        authenticationStatus.value = AuthenticationStatus.USER_DECLINED
    }

}
