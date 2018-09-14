package com.rockwellstudios.mychat.ui.main.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseBottomBarFragment
import com.rockwellstudios.mychat.base.BaseFragment

class InboxFragment : BaseBottomBarFragment(),InboxContract.View {

    companion object {
        fun newInstance() = InboxFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_inbox,container,false)
    }

    override fun getBottomBarItem() = R.id.action_inbox


}