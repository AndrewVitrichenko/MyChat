package com.rockwellstudios.mychat.ui.auth.registration

import android.os.Bundle
import android.view.View
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseAuthFragment
import kotlinx.android.synthetic.main.layout_auth.*
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationFragment : BaseAuthFragment(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationContract.Presenter

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun getLayoutContainer(): Int = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignIn.visibility = View.GONE
        presenter.attach()
    }

    override fun moveToLogin() {
        activity?.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

}