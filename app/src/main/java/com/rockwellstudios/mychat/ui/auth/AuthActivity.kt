package com.rockwellstudios.mychat.ui.auth

import android.content.Intent
import android.os.Bundle

import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseActivity
import com.rockwellstudios.mychat.common.IP_LOCAL_HOST
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import com.rockwellstudios.mychat.ui.main.MainActivity

import io.socket.client.IO
import io.socket.client.Socket

import java.net.URISyntaxException
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class AuthActivity : BaseActivity(), AuthContract.View {

    @Inject
    lateinit var presenter: AuthContract.Presenter

    override fun getFragmentContainer() = R.id.activity_main_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach()
    }

    override fun showLoginScreen() {
        showFragment(LoginFragment.newInstance(),true)
    }

    override fun showMainScreen() {
        val coreScreen = Intent(this, MainActivity::class.java)
        startActivity(coreScreen)
        finish()
    }


    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}