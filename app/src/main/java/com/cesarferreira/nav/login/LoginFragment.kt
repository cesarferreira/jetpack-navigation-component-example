package com.cesarferreira.nav.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import com.cesarferreira.nav.BaseFragment
import com.cesarferreira.nav.R
import com.cesarferreira.nav.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.cesarferreira.nav.login.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.cesarferreira.nav.observe
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.loginButton

class LoginFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.login_fragment

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d("lifecycle", "handleOnBackPressed")
            // activity?.finish()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
            // viewModel.authenticate(usernameEditText.text.toString(), passwordEditText.text.toString())
            loginViewModel.authenticate("yomama", "yomama")
        }
    }
}
