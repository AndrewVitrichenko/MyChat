package com.rockwellstudios.mychat.ui.main.friends.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment

class FindFriendsFragment : BaseFragment(),FindFriendsContract.View {

    companion object {
        fun newInstance() = FindFriendsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find_friends,container,false)
    }

    override fun getFragmentTag(): String  = javaClass.canonicalName
}