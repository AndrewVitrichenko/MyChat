package com.rockwellstudios.mychat.ui.main.friends.find.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.rockwellstudios.mychat.common.FIREBASE_PATH_FRIEND_REQUEST_RECEIVED
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
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_SENT) val friendsRequestsSentDatabase: DatabaseReference,
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_RECEIVED) val friendsRequestsReceivedDatabase: DatabaseReference,
                                                  val socketConnector: SocketConnector,
                                                  val currentUserEmail: String,
                                                  val usersPublisher: PublishSubject<MutableList<User?>>,
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_SENT)
                                                  val friendsRequestsSentPublisher:PublishSubject<HashMap<String,User?>>,
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_RECEIVED)
                                                  val friendsRequestsReceivedPublisher:PublishSubject<HashMap<String,User?>>) : FriendsDataSource {

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

    override fun listenFriendsRequestsSentEvents(): PublishSubject<HashMap<String, User?>> {
        friendsRequestsSentDatabase.addValueEventListener(friendsRequestsSentEventListener)
        return friendsRequestsSentPublisher
    }

    override fun listenFriendsRequestsReceivedEvents(): PublishSubject<HashMap<String, User?>> {
        friendsRequestsReceivedDatabase.addValueEventListener(friendsRequestsReceivedEventListener)
        return friendsRequestsReceivedPublisher
    }


    override fun sendFriendRequest(user: User?) {
        friendsRequestsSentDatabase.child(encodeEmail(user?.email!!)).setValue(user)
    }

    override fun cancelFriendRequest(user: User?) {
        friendsRequestsSentDatabase.child(encodeEmail(user?.email!!)).removeValue()
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

    private var friendsRequestsSentEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val friendsRequestsMap: HashMap<String, User?> = HashMap()
            dataSnapshot.children.map { item -> item.getValue(User::class.java) }
                    .onEach { user ->
                        friendsRequestsMap[user?.email!!] = user
                    }
            friendsRequestsSentPublisher.onNext(friendsRequestsMap)

        }
    }

    private var friendsRequestsReceivedEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val friendsRequestsMap: HashMap<String, User?> = HashMap()
            dataSnapshot.children.map { item -> item.getValue(User::class.java) }
                    .onEach { user ->
                        friendsRequestsMap[user?.email!!] = user
                    }
            friendsRequestsReceivedPublisher.onNext(friendsRequestsMap)

        }
    }

    override fun stopEventsListening() {
        usersDatabase.removeEventListener(usersEventListener)
        friendsRequestsSentDatabase.removeEventListener(friendsRequestsSentEventListener)
        friendsRequestsReceivedDatabase.removeEventListener(friendsRequestsReceivedEventListener)
    }

}