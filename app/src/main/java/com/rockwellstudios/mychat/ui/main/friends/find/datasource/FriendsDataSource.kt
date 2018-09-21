package com.rockwellstudios.mychat.ui.main.friends.find.datasource

import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface FriendsDataSource {

    fun listenUserEvents() : PublishSubject<MutableList<User?>>

    fun listenFriendsRequestsSentEvents() : PublishSubject<HashMap<String,User?>>

    fun listenFriendsRequestsReceivedEvents() : PublishSubject<HashMap<String,User?>>

    fun sendFriendRequest(user: User?)

    fun cancelFriendRequest(user: User?)

    fun addOrRemoveFriendRequest(email: String, requestCode: Int) : Observable<Int>

    fun stopEventsListening()

}