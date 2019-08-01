package com.rockwellstudios.mychat.ui.auth.registration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseAuthFragment
import com.rockwellstudios.mychat.common.Status
import com.rockwellstudios.mychat.ui.auth.entities.UserState
import kotlinx.android.synthetic.main.layout_auth.*
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationFragment : BaseAuthFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var registrationViewModel: RegistrationViewModel

    private val registrationStateObserver = Observer<UserState> { userState ->
        when(userState.status){
            Status.SUCCESS -> {
                showLoading(false)
                moveToLogin()
            }
            Status.ERROR -> {
                showLoading(false)
                showMessage(userState.message!!)
            }
            Status.LOADING -> showLoading(true)
        }
    }

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun getLayoutContainer(): Int = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel = ViewModelProviders.of(this,viewModelFactory).get(RegistrationViewModel::class.java)
        registrationViewModel.getRegistrationState().observe(viewLifecycleOwner,registrationStateObserver)
        btnSignUp.setOnClickListener {
            registrationViewModel.onRegistrationButtonClicked(editTextUserName.text.toString(),
                    editTextEmail.text.toString(),editTextPassword.text.toString())
        }
        btnSignIn.visibility = View.GONE
    }

    private fun moveToLogin() {
        activity?.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        btnSignUp.setOnClickListener(null)
        registrationViewModel.getRegistrationState().removeObserver(registrationStateObserver)
    }

}