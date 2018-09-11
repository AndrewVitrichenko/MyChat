package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.di.AuthModule
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
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

}