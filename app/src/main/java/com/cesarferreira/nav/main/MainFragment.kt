package com.cesarferreira.nav.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cesarferreira.nav.BaseFragment
import com.cesarferreira.nav.R
import com.cesarferreira.nav.login.LoginViewModel
import com.cesarferreira.nav.observe
import kotlinx.android.synthetic.main.main_fragment.logoutButton
import kotlinx.android.synthetic.main.main_fragment.openProfileButton

class MainFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.main_fragment

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("lifecycle", "MainFragment -> onActivityCreated")

        observe(loginViewModel.authenticationState, { authenticationState ->
            when (authenticationState) {
//                LoginViewModel.AuthenticationState.AUTHENTICATED -> showWelcomeMessage()
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.login_fragment)
            }
        })

        openProfileButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.navigateToProfileFragment())
        }

        logoutButton.setOnClickListener { loginViewModel.logout() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifecycle", "MainFragment -> onDestroyView")
    }

    private fun showWelcomeMessage() {
        Toast.makeText(context, "WELCOME", Toast.LENGTH_LONG).show()
    }
}
