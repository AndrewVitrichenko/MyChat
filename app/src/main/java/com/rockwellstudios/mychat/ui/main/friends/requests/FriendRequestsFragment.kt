package com.rockwellstudios.mychat.ui.main.friends.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment

class FriendRequestsFragment : BaseFragment(),FriendRequestsContract.View {

    companion object {
        fun newInstance() = FriendRequestsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends_requests,container,false)
    }


    override fun getFragmentTag(): String = javaClass.canonicalName
}