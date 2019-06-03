package com.cesarferreira.nav.login

import android.os.Bundle
import android.view.View
import com.cesarferreira.nav.BaseFragment
import com.cesarferreira.nav.R
import com.cesarferreira.nav.observe
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class LoginFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.login_fragment

    private lateinit var loginViewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = getSharedViewModel(from = { requireActivity() })


        // TODO make it backpress quit the app

        observe(loginViewModel.authenticationState, { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION ->
                    Snackbar.make(
                            view!!,
                            R.string.invalid_credentials,
                            Snackbar.LENGTH_SHORT
                    ).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            // viewModel.authenticate(usernameEditText.text.toString(), passwordEditText.text.toString())
            loginViewModel.authenticate("yomama", "yomama")
        }
    }
}
