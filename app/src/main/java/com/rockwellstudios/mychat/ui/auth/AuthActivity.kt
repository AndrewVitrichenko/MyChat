package com.rockwellstudios.mychat.ui.auth

import android.os.Bundle
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseActivity
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class AuthActivity : BaseActivity(),AuthContract.View {

    //    private var mSocket: Socket? = null
    @Inject
    private lateinit var presenter: AuthContract.Presenter


    override fun getFragmentContainer() = R.id.activity_login_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mSocket =
//                try {
//                    IO.socket(IP_LOCAL_HOST)
//                } catch (e: URISyntaxException) {
//                    null
//                }
//        mSocket?.connect()

    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun showLoading(loading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoginScreen() {
       showFragment(LoginFragment.newInstance())
    }

    override fun showMainScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onDestroy() {
//        mSocket?.disconnect()
        super.onDestroy()
    }
}