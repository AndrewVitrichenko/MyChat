package com.rockwellstudios.mychat.ui.main.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rockwellstudios.mychat.common.FIREBASE_PATH_FRIEND_REQUEST_SENT
import com.rockwellstudios.mychat.common.FIREBASE_PATH_USERS
import com.rockwellstudios.mychat.common.USER_EMAIL
import com.rockwellstudios.mychat.common.encodeEmail
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Named


@Module
class UserDataModule {

    @Provides
    @Named(FIREBASE_PATH_USERS)
    fun provideFirebaseDatabaseUsers () : DatabaseReference{
        return FirebaseDatabase.getInstance().reference.child(FIREBASE_PATH_USERS)
    }

    @Provides
    @Named(FIREBASE_PATH_FRIEND_REQUEST_SENT)
    fun provideFirebaseDatabaseFriendRequests (userEmail : String) : DatabaseReference{
        return FirebaseDatabase.getInstance().reference.child(FIREBASE_PATH_FRIEND_REQUEST_SENT)
                .child(encodeEmail(userEmail))
    }

    @Provides
    fun provideUserEmail(preferenceDataSource: PreferenceDataSource) = preferenceDataSource.getString(USER_EMAIL,"")

    @Provides
    fun provideUsersEventsPublishSubject() = PublishSubject.create<MutableList<User?>>()

    @Provides
    fun provideFriendsRequestsPublishSubject() = PublishSubject.create<HashMap<String,User>>()
}