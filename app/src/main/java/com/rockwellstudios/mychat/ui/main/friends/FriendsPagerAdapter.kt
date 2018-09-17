package com.rockwellstudios.mychat.ui.main.friends

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsFragment
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsFragment

class FriendsPagerAdapter constructor(fm : FragmentManager,
                                      private val context: Context?): FragmentStatePagerAdapter(fm) {

    private val ITEM_COUNT = 3

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = UserFriendsFragment.newInstance()
            1 -> fragment = FriendRequestsFragment.newInstance()
            2 -> fragment = FindFriendsFragment.newInstance()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = ""
        when(position){
            0 -> title = context?.getString(R.string.title_friends)
            1 -> title = context?.getString(R.string.title_friends_requests)
            2 -> title = context?.getString(R.string.title_find_friends)
        }
        return title
    }

    override fun getCount(): Int  = ITEM_COUNT
}