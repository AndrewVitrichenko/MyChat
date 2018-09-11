package com.rockwellstudios.mychat.ui.auth.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.AuthContract
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginFragment : BaseFragment(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach()
    }

    override fun emailInputStream(): Observable<String>  = RxTextView.textChanges(editTextEmail)
            .map {it.toString()}

    override fun passwordInputStream(): Observable<String> = RxTextView.textChanges(editTextPassword)
            .map {it.toString()}

    override fun signInButtonClick(): Observable<Any> = RxView.clicks(btnSignIn)

    override fun moveToCoreScreen() {
        activity?.let {
            val authView : AuthContract.View = it as AuthContract.View
            authView.showMainScreen()
        }
    }

    override fun signUpButtonClick(): Observable<Any> = RxView.clicks(btnSignUp)

    override fun showSignUpScreen() {
        activity?.let {
            val authActivity : AuthActivity = it as AuthActivity
            authActivity.showFragment(RegistrationFragment.newInstance(),true)
        }
    }

    override fun showLoading(loading: Boolean) {
        progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}