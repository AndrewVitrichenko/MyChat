package com.rockwellstudios.mychat

import android.content.Context
import androidx.multidex.MultiDex
import com.rockwellstudios.mychat.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by user on 23.03.18.
 */
class MainApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }
}