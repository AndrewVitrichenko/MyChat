package com.rockwellstudios.mychat.ui.main.friends.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment

class UserFriendsFragment : BaseFragment(),UserFriendsContract.View {

    companion object {
        fun newInstance() = UserFriendsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_friends,container,false)
    }

    override fun getFragmentTag(): String = javaClass.canonicalName
}