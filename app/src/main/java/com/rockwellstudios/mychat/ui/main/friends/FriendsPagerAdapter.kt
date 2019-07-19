package com.rockwellstudios.mychat.ui.main.friends

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsFragment
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsFragment

class FriendsPagerAdapter constructor(fm : androidx.fragment.app.FragmentManager,
                                      private val context: Context?): androidx.fragment.app.FragmentStatePagerAdapter(fm) {

    private val ITEM_COUNT = 3

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        var fragment: androidx.fragment.app.Fragment? = null
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