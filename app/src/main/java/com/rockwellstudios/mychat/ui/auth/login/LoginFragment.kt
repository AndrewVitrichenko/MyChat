package com.rockwellstudios.mychat.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseAuthFragment
import com.rockwellstudios.mychat.common.Status
import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.entities.UserState
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_auth.*
import javax.inject.Inject

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginFragment : BaseAuthFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    private val loginStateObserver = Observer<UserState> { userState ->
        when(userState.status){
            Status.SUCCESS -> {
                showLoading(false)
                moveToCoreScreen()
            }
            Status.ERROR -> {
                showLoading(false)
                showMessage(userState.message!!)
            }
            Status.LOADING -> showLoading(true)
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutContainer(): Int = R.layout.fragment_login

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)
        loginViewModel.getLoginState().observe(viewLifecycleOwner,loginStateObserver)
        editTextUserName.visibility = View.GONE
        btnSignIn.setOnClickListener {
            loginViewModel.onSignInButtonClick(editTextEmail.text.toString(),editTextPassword.text.toString())
        }
        btnSignUp.setOnClickListener {
            showSignUpScreen()
        }
    }

    private fun moveToCoreScreen() {
        activity?.let {
            val authActivity : AuthActivity = it as AuthActivity
            authActivity.showMainScreen()
        }
    }

    private fun showSignUpScreen() {
        activity?.let {
            val authActivity : AuthActivity = it as AuthActivity
            authActivity.showFragment(RegistrationFragment.newInstance(),true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        btnSignIn.setOnClickListener(null)
        btnSignUp.setOnClickListener(null)
        loginViewModel.getLoginState().removeObserver(loginStateObserver)
    }
}