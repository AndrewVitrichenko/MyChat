package com.rockwellstudios.mychat.ui.auth.login

import android.os.Bundle
import android.view.View
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseAuthFragment
import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.AuthContract
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_auth.*
import javax.inject.Inject

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginFragment : BaseAuthFragment(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutContainer(): Int = R.layout.fragment_login

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextUserName.visibility = View.GONE
        presenter.attach()
    }

    override fun signInButtonClick(): Observable<Any> = viewClick(btnSignIn).share()

    override fun moveToCoreScreen() {
        activity?.let {
            val authView: AuthContract.View = it as AuthContract.View
            authView.showMainScreen()
        }
    }

    override fun showSignUpScreen() {
        activity?.let {
            val authActivity : AuthActivity = it as AuthActivity
            authActivity.showFragment(RegistrationFragment.newInstance(),true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}