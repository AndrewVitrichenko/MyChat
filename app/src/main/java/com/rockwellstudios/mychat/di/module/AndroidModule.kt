package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.di.AuthModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by user on 23.03.18.
 */
@Module
abstract class AndroidModule {

    @ContributesAndroidInjector(modules = arrayOf(AuthModule::class))
    abstract fun authActivityInjector(): AuthActivity

}