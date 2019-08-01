package com.rockwellstudios.mychat.ui.main

import android.os.Bundle
import com.rockwellstudios.mychat.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rockwellstudios.mychat.base.BaseBottomBarActivity
import com.rockwellstudios.mychat.ui.main.friends.FriendsFragment
import com.rockwellstudios.mychat.ui.main.inbox.InboxFragment
import com.rockwellstudios.mychat.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by user on 23.03.18.
 */
class MainActivity : BaseBottomBarActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomBar()
        showFragment(InboxFragment.newInstance(),true)
    }

    override fun getBottomBar(): BottomNavigationView = bottomNavigation

    override fun getFragmentContainer(): Int = R.id.activity_main_fragment_container

    private fun initBottomBar() {
        getBottomBar().setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_inbox -> showFragment(InboxFragment.newInstance(),true)
                R.id.action_friends -> showFragment(FriendsFragment.newInstance(),true)
                R.id.action_profile -> showFragment(ProfileFragment.newInstance(),true)
            }
            true
        }
    }

}