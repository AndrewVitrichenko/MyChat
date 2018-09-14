package com.rockwellstudios.mychat.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseBottomBarFragment
import com.rockwellstudios.mychat.base.BaseFragment

class ProfileFragment : BaseBottomBarFragment(),ProfileContract.View {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun getBottomBarItem() = R.id.action_profile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

}