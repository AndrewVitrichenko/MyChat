package com.rockwellstudios.mychat.ui.main.friends.find

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import com.rockwellstudios.mychat.ui.main.friends.find.list.FindFriendsAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_find_friends.*
import javax.inject.Inject

class FindFriendsFragment : BaseFragment(), FindFriendsContract.View, FindFriendsAdapter.OnUserClickListener {

    @Inject
    lateinit var presenter: FindFriendsContract.Presenter

    lateinit var findFriendsAdapter: FindFriendsAdapter

    companion object {
        fun newInstance() = FindFriendsFragment()
    }

    override fun getFragmentTag(): String = javaClass.canonicalName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find_friends, container, false)
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

    override fun searchInputStream(): Observable<String> = RxTextView.textChanges(searchEditText)
            .map { searchInput -> searchInput.toString() }

    override fun setUsersList(userList: MutableList<User?>) {
        findFriendsAdapter.setUsers(userList)
    }

    override fun onUserClick(user: User?) {
        presenter.onUserClick(user)
    }

    override fun setFriendsRequestSentMap(friendsRequestsSentMap: HashMap<String, User?>) {
        findFriendsAdapter.setFriendRequestSentMap(friendsRequestsSentMap)
    }

    override fun setFriendsRequestReceivedMap(friendsRequestsReceivedMap: HashMap<String, User?>) {
        findFriendsAdapter.setFriendRequestReceivedMap(friendsRequestsReceivedMap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        findFriendsAdapter.setOnUserClickListener(null)
        presenter.detach()
    }
}