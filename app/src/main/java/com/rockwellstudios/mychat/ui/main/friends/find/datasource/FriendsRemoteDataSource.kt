package com.rockwellstudios.mychat.ui.main.friends.find.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.rockwellstudios.mychat.common.FIREBASE_PATH_FRIEND_REQUEST_SENT
import com.rockwellstudios.mychat.common.FIREBASE_PATH_USERS
import com.rockwellstudios.mychat.common.encodeEmail
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import com.rockwellstudios.mychat.utils.SocketConnector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Named

class FriendsRemoteDataSource @Inject constructor(@Named(FIREBASE_PATH_USERS) val usersDatabase: DatabaseReference,
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_SENT) val friendsRequestsDatabase: DatabaseReference,
                                                  val socketConnector: SocketConnector,
                                                  val currentUserEmail: String,
                                                  val usersPublisher: PublishSubject<MutableList<User?>>,
                                                  val friendsRequestsPublisher:PublishSubject<HashMap<String,User?>>) : FriendsDataSource {
    override fun addOrRemoveFriendRequest(email: String, requestCode: Int): Observable<Int> {
        return Observable.just(requestCode)
                .doOnNext {
                    try {
                        val request = JSONObject()
                        request.apply {
                            put("email",email)
                            put("userEmail",currentUserEmail)
                            put("requestCode",it)
                            socketConnector.initConnection()?.emit("friendRequest",request)
                        }
                    }catch (e: Exception){

                    }
                }
    }


    override fun listenUserEvents(): PublishSubject<MutableList<User?>> {
        usersDatabase.addValueEventListener(usersEventListener)
        return usersPublisher
    }

    override fun listenFriendsRequestsEvents(): PublishSubject<HashMap<String, User?>> {
        friendsRequestsDatabase.addValueEventListener(friendsRequestsEventListener)
        return friendsRequestsPublisher
    }


    override fun sendFriendRequest(user: User?) {
        friendsRequestsDatabase.child(encodeEmail(user?.email!!)).setValue(user)
    }

    override fun cancelFriendRequest(user: User?) {
        friendsRequestsDatabase.child(encodeEmail(user?.email!!)).removeValue()
    }

    private var usersEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val filteredUsers = dataSnapshot.children.map { item -> item.getValue(User::class.java) }
                    .filter { user -> user?.email != currentUserEmail }
                    .toMutableList()
            usersPublisher.onNext(filteredUsers)

        }
    }

    private var friendsRequestsEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val friendsRequestsMap: HashMap<String, User?> = HashMap()
            dataSnapshot.children.map { item -> item.getValue(User::class.java) }
                    .onEach { user ->
                        friendsRequestsMap[user?.email!!] = user
                    }
            friendsRequestsPublisher.onNext(friendsRequestsMap)

        }
    }

    override fun stopUserEventsListening() {
        usersDatabase.removeEventListener(usersEventListener)
    }

    override fun stopFriendRequestsEventsListening() {
        friendsRequestsDatabase.removeEventListener(friendsRequestsEventListener)
    }

}