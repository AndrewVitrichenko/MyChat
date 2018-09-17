package com.rockwellstudios.mychat.ui.main.di

import com.rockwellstudios.mychat.ui.main.friends.FriendsContract
import com.rockwellstudios.mychat.ui.main.friends.FriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.FriendsPresenter
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsContract
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsPresenter
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsContract
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsFragment
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsPresenter
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsContract
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsPresenter
import com.rockwellstudios.mychat.ui.main.inbox.InboxContract
import com.rockwellstudios.mychat.ui.main.inbox.InboxFragment
import com.rockwellstudios.mychat.ui.main.inbox.InboxPresenter
import com.rockwellstudios.mychat.ui.main.profile.ProfileContract
import com.rockwellstudios.mychat.ui.main.profile.ProfileFragment
import com.rockwellstudios.mychat.ui.main.profile.ProfilePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun bindFriendsView(addFriendsFragment: FriendsFragment): FriendsContract.View

    @Binds
    abstract fun bindFriendsPresenter(addFriendsPresenter: FriendsPresenter): FriendsContract.Presenter

    @Binds
    abstract fun bindFindFriendsView(findFriendsFragment: FindFriendsFragment): FindFriendsContract.View

    @Binds
    abstract fun bindFindFriendsPresenter(findFriendsPresenter: FindFriendsPresenter): FindFriendsContract.Presenter

    @Binds
    abstract fun bindUserFriendsView(userFriendsFragment: UserFriendsFragment): UserFriendsContract.View

    @Binds
    abstract fun bindUserFriendsPresenter(userFriendsPresenter: UserFriendsPresenter): UserFriendsContract.Presenter

    @Binds
    abstract fun bindFriendsRequestsView(friendsRequestsFragment: FriendRequestsFragment): FriendRequestsContract.View

    @Binds
    abstract fun bindFriendsRequestsPresenter(friendRequestsPresenter: FriendRequestsPresenter): FriendRequestsContract.Presenter

    @Binds
    abstract fun bindInboxView(inboxFragment: InboxFragment): InboxContract.View

    @Binds
    abstract fun bindInboxPresenter(inboxPresenter: InboxPresenter) : InboxContract.Presenter

    @Binds
    abstract fun bindProfileView(profileFragment: ProfileFragment) : ProfileContract.View

    @Binds
    abstract fun bindProfilePresenter(profilePresenter: ProfilePresenter) : ProfileContract.Presenter

}