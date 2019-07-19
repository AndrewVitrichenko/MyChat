package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract fun authActivityInjector(): AuthActivity

    @ContributesAndroidInjector()
    abstract fun mainActivityInjector(): MainActivity

}