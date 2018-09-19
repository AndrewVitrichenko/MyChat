package com.rockwellstudios.mychat.ui.main.friends.find

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import com.rockwellstudios.mychat.ui.main.friends.find.list.FindFriendsAdapter
import kotlinx.android.synthetic.main.fragment_find_friends.*
import javax.inject.Inject

class FindFriendsFragment : BaseFragment(),FindFriendsContract.View, FindFriendsAdapter.OnUserClickListener {

    @Inject
    lateinit var presenter: FindFriendsContract.Presenter

    lateinit var findFriendsAdapter: FindFriendsAdapter

    companion object {
        fun newInstance() = FindFriendsFragment()
    }

    override fun getFragmentTag(): String  = javaClass.canonicalName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find_friends,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter.attach()

    }

    private fun initList() {
        findFriendsAdapter = FindFriendsAdapter()
        findFriendsAdapter.setOnUserClickListener(this)
        findFriendsRecyclerView.layoutManager = LinearLayoutManager(context)
        findFriendsRecyclerView.adapter = findFriendsAdapter
    }

    override fun setUsersList(userList: MutableList<User>) {
        findFriendsAdapter.setUsers(userList)
    }

    override fun onUserClick(user: User) {
        presenter.onUserClick(user)
//        Toast.makeText(context,user.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        findFriendsAdapter.setOnUserClickListener(null)
        presenter.detach()
    }
}