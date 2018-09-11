package com.rockwellstudios.mychat.base

import android.widget.Toast
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), BaseView {



    override fun showLoading(loading: Boolean) {

    }

    override fun showMessage(message: String) {
        context?.let {
            Toast.makeText(it,message,Toast.LENGTH_SHORT).show()
        }
    }
}