package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.di.AuthDataModule
import com.rockwellstudios.mychat.ui.auth.di.AuthModule
import com.rockwellstudios.mychat.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(AuthDataModule::class))
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(AuthModule::class))
    abstract fun authActivityInjector(): AuthActivity

    @ContributesAndroidInjector()
    abstract fun mainActivityInjector(): MainActivity

}