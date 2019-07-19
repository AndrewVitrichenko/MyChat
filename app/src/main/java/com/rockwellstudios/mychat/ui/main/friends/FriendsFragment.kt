package com.rockwellstudios.mychat.ui.main.friends

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseBottomBarFragment
import kotlinx.android.synthetic.main.fragment_friends.*
import javax.inject.Inject

class FriendsFragment : BaseBottomBarFragment(), FriendsContract.View {

    @Inject
    lateinit var presenter : FriendsContract.Presenter

    lateinit var pagerAdapter : FriendsPagerAdapter

    companion object {
        fun newInstance() = FriendsFragment()
    }

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun getBottomBarItem(): Int = R.id.action_friends

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach()

    }

    override fun initPager() {
        pagerAdapter = FriendsPagerAdapter(childFragmentManager,context)
        friendsViewPager.adapter = pagerAdapter
        friendsTabLayout.setupWithViewPager(friendsViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

}