package com.rockwellstudios.mychat.ui.auth.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.entity.AuthEntities
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginFragment : BaseFragment() {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignIn.setOnClickListener { v ->
            val authBody = AuthEntities.AuthBody(
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString()
            )
            presenter.onSignInButtonClick(authBody)}
    }
}