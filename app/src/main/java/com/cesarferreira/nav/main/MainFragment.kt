package com.cesarferreira.nav.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cesarferreira.nav.BaseFragment
import com.cesarferreira.nav.R
import com.cesarferreira.nav.login.LoginViewModel
import com.cesarferreira.nav.observe
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class MainFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.main_fragment

    private lateinit var loginViewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = getSharedViewModel(from = { requireActivity() })

        observe(loginViewModel.authenticationState, { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> showWelcomeMessage()
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.login_fragment)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openProfileButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.navigateToProfileFragment())
        }

        logoutButton.setOnClickListener {loginViewModel.logout()}
    }

    private fun showWelcomeMessage() {
        Toast.makeText(context, "WELCOME", Toast.LENGTH_LONG).show()
    }
}
