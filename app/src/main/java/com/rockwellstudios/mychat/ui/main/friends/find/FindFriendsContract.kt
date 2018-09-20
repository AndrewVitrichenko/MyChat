package com.rockwellstudios.mychat.ui.main.friends.find

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User

interface FindFriendsContract {

    interface View : BaseView {

        fun setUsersList(userList: MutableList<User?>)

        fun setFriendsRequestSentMap(friendsRequests: HashMap<String, User?>)

    }

    interface Presenter : BasePresenter {
        fun onUserClick(user: User?)
    }
}