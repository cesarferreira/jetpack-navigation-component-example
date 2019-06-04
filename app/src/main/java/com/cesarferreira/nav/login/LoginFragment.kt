package com.cesarferreira.nav.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.cesarferreira.nav.BaseFragment
import com.cesarferreira.nav.R
import com.cesarferreira.nav.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.cesarferreira.nav.login.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.cesarferreira.nav.observe
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.link_signup
import kotlinx.android.synthetic.main.login_fragment.loginButton
import kotlinx.android.synthetic.main.login_fragment.passwordEditText
import kotlinx.android.synthetic.main.login_fragment.usernameEditText

class LoginFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.login_fragment

    private val loginViewModel: LoginViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // FAKE
        usernameEditText.setText("demo")
        passwordEditText.setText("demo")

        observe(loginViewModel.authenticationState, { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> navController.popBackStack()
                INVALID_AUTHENTICATION ->
                    Snackbar.make(
                        view!!,
                        R.string.invalid_credentials,
                        Snackbar.LENGTH_SHORT
                    ).show()
            }
        })

        loginButton.setOnClickListener {
            loginViewModel.authenticate(usernameEditText.text.toString(), passwordEditText.text.toString())
        }

        link_signup.setOnClickListener { navController.navigate(LoginFragmentDirections.toForgotPassword()) }
    }
}
