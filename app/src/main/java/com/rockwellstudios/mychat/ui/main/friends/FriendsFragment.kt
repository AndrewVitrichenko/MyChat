package com.rockwellstudios.mychat.ui.main.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseBottomBarFragment

class FriendsFragment : BaseBottomBarFragment(), FriendsContract.View {


    companion object {
        fun newInstance() = FriendsFragment()
    }

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun getBottomBarItem(): Int = R.id.action_friends

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends,container,false)
    }
}