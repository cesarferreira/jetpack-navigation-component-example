package com.cesarferreira.nav.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    init {
        Log.d("lifecycle", "LoginViewModel: INIT")
    }

    internal val authenticationState = MutableLiveData<AuthenticationState>().apply {
        value = AuthenticationState.UNAUTHENTICATED
    }

    private var username = ""

    enum class AuthenticationState {
        UNAUTHENTICATED, // Initial state, the user needs to authenticate
        AUTHENTICATED, // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    fun authenticate(username: String, password: String) {
        if (passwordIsValidForUsername(username, password)) {
            this.username = username
            authenticationState.setValue(AuthenticationState.AUTHENTICATED)
        } else {
            authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION)
        }
    }

    fun logout() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    private fun passwordIsValidForUsername(username: String, password: String): Boolean {
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("lifecycle", "LoginViewModel: OnCleared")

    }
}