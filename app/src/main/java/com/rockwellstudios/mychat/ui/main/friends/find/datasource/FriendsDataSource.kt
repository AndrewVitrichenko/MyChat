package com.rockwellstudios.mychat.ui.main.friends.find.datasource

import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import io.reactivex.subjects.PublishSubject

interface FriendsDataSource {

    fun listenEvents() : PublishSubject<Any>

    fun sendFriendRequest(user: User)

    fun stopEventListening()
}