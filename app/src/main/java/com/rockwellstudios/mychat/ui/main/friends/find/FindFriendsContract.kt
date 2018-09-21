package com.rockwellstudios.mychat.ui.main.friends.find

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import io.reactivex.Observable

interface FindFriendsContract {

    interface View : BaseView {

        fun searchInputStream(): Observable<String>

        fun setUsersList(userList: MutableList<User?>)

        fun setFriendsRequestSentMap(friendsRequestsSentMap: HashMap<String, User?>)

        fun setFriendsRequestReceivedMap(friendsRequestsReceivedMap: HashMap<String, User?>)

    }

    interface Presenter : BasePresenter {
        fun onUserClick(user: User?)
    }
}