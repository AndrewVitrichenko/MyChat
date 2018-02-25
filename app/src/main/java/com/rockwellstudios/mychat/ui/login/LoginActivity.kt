package com.rockwellstudios.mychat.ui.login

import android.os.Bundle
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseActivity

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginActivity : BaseActivity() {
    override fun getFragmentContainer(): Int {
        return R.id.activity_login_fragment_container
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}