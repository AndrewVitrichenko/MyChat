package com.rockwellstudios.mychat.ui.main.di

import com.rockwellstudios.mychat.ui.main.addfriends.AddFriendsContract
import com.rockwellstudios.mychat.ui.main.addfriends.AddFriendsFragment
import com.rockwellstudios.mychat.ui.main.addfriends.AddFriendsPresenter
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
    abstract fun bindAddFriendsView(addFriendsFragment: AddFriendsFragment): AddFriendsContract.View

    @Binds
    abstract fun bindAddFriendsPresenter(addFriendsPresenter: AddFriendsPresenter): AddFriendsContract.Presenter

    @Binds
    abstract fun bindInboxView(inboxFragment: InboxFragment): InboxContract.View

    @Binds
    abstract fun bindInboxPresenter(inboxPresenter: InboxPresenter) : InboxContract.Presenter

    @Binds
    abstract fun bindProfileView(profileFragment: ProfileFragment) : ProfileContract.View

    @Binds
    abstract fun bindProfilePresenter(profilePresenter: ProfilePresenter) : ProfileContract.Presenter

}