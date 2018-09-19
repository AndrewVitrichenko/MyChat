package com.rockwellstudios.mychat.ui.main.friends.find.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.rockwellstudios.mychat.common.FIREBASE_PATH_FRIEND_REQUEST_SENT
import com.rockwellstudios.mychat.common.FIREBASE_PATH_USERS
import com.rockwellstudios.mychat.common.USER_EMAIL
import com.rockwellstudios.mychat.common.encodeEmail
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Named

class FriendsRemoteDataSource @Inject constructor(@Named(FIREBASE_PATH_USERS) val usersDatabase: DatabaseReference,
                                                  @Named(FIREBASE_PATH_FRIEND_REQUEST_SENT) val friendsRequestsDatabase: DatabaseReference,
                                                  preferenceDataSource: PreferenceDataSource,
                                                  val sendSubject: PublishSubject<Any>) : FriendsDataSource {

    private val currentUserEmail: String = preferenceDataSource.getString(USER_EMAIL, "")


    override fun listenEvents(): PublishSubject<Any> {
        usersDatabase.addValueEventListener(usersEventListener)
        friendsRequestsDatabase.addValueEventListener(friendsRequestsEventListener)
        return sendSubject
    }

    override fun sendFriendRequest(user: User) {
       friendsRequestsDatabase.child(encodeEmail(user.email)).setValue(user)
    }

    private var usersEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val filteredUsers = dataSnapshot.children.map { dataSnapshot -> dataSnapshot.getValue(User::class.java) }
                    .filter { user -> user?.email != currentUserEmail }
            sendSubject.onNext(filteredUsers)

        }
    }

    private var friendsRequestsEventListener = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            val filteredUsers = dataSnapshot.children.map { dataSnapshot -> dataSnapshot.getValue(User::class.java) }
//                    .filter { user -> user?.email != currentUserEmail }
//                    .toList()
//            sendSubject.onNext(filteredUsers)

        }
    }


    override fun stopEventListening() {
        usersDatabase.removeEventListener(usersEventListener)
        friendsRequestsDatabase.removeEventListener(friendsRequestsEventListener)
    }
}