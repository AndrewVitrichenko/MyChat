package com.rockwellstudios.mychat.ui.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_login,container,false)
        return rootView
    }
}