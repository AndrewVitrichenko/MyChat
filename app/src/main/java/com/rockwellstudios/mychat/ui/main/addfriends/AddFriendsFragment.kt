package com.rockwellstudios.mychat.ui.main.addfriends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseBottomBarFragment
import com.rockwellstudios.mychat.base.BaseFragment

class AddFriendsFragment : BaseBottomBarFragment(), AddFriendsContract.View {


    companion object {
        fun newInstance() = AddFriendsFragment()
    }

    override fun getBottomBarItem(): Int = R.id.action_friends

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_friends,container,false)
    }
}