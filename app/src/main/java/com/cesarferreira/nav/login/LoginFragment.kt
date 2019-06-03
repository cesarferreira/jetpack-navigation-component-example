package com.cesarferreira.nav.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cesarferreira.nav.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.loginButton

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // requireActivity().getOnBackPressedDispatcher().addCallback(viewLifecycleOwner) {
        //     viewModel.refuseAuthentication()
        //     navController.popBackStack(R.id.main_fragment, false)
        // }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            // viewModel.authenticate(usernameEditText.text.toString(), passwordEditText.text.toString())
            viewModel.authenticate("yomama", "yomama")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
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
}
