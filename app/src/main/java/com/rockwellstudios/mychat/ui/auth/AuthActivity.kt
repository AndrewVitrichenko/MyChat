package com.rockwellstudios.mychat.ui.auth

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseActivity
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import com.rockwellstudios.mychat.ui.main.MainActivity
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class AuthActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var authViewModel: AuthViewModel

    private val loggedInObserver = Observer<Boolean> { loggedIn ->
        if (loggedIn) {
            showMainScreen()
        } else {
            showLoginScreen()
        }
    }

    override fun getFragmentContainer() = R.id.activity_auth_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
        authViewModel.getAuthState().observe(this, loggedInObserver)
    }

    fun showLoginScreen() {
        showFragment(LoginFragment.newInstance(), true)
    }

    fun showMainScreen() {
        val coreScreen = Intent(this, MainActivity::class.java)
        startActivity(coreScreen)
        finish()
    }

    override fun onDestroy() {
        authViewModel.getAuthState().removeObserver(loggedInObserver)
        super.onDestroy()
    }


}