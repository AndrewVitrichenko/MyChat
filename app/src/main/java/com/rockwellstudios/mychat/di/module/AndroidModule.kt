package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.di.AuthModule
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
import com.rockwellstudios.mychat.ui.main.MainActivity
import com.rockwellstudios.mychat.ui.main.friends.FriendsFragment
import com.rockwellstudios.mychat.ui.main.di.MainModule
import com.rockwellstudios.mychat.ui.main.di.UserDataModule
import com.rockwellstudios.mychat.ui.main.di.UserDataModule_ProvideFirebaseDatabaseFriendRequestsFactory
import com.rockwellstudios.mychat.ui.main.friends.find.FindFriendsFragment
import com.rockwellstudios.mychat.ui.main.friends.requests.FriendRequestsFragment
import com.rockwellstudios.mychat.ui.main.friends.user.UserFriendsFragment
import com.rockwellstudios.mychat.ui.main.inbox.InboxFragment
import com.rockwellstudios.mychat.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by user on 23.03.18.
 */
@Module
abstract class AndroidModule {

    @ContributesAndroidInjector(modules = arrayOf(AuthModule::class))
    abstract fun authActivityInjector(): AuthActivity

    @ContributesAndroidInjector(modules = arrayOf(AuthModule::class))
    abstract fun loginFragmentInjector(): LoginFragment

    @ContributesAndroidInjector(modules = arrayOf(AuthModule::class))
    abstract fun registrationFragmentInjector(): RegistrationFragment

    @ContributesAndroidInjector()
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun inboxFragmentInjector(): InboxFragment

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun friendsFragmentInjector(): FriendsFragment

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun userFriendsFragmentInjector(): UserFriendsFragment

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun friendsRequestsFragmentInjector(): FriendRequestsFragment

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun findFriendsFragmentInjector(): FindFriendsFragment

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class, UserDataModule::class))
    abstract fun profileFragmentInjector(): ProfileFragment

}