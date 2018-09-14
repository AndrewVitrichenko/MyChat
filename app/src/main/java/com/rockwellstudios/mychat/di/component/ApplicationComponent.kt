package com.rockwellstudios.mychat.di.component

import com.rockwellstudios.mychat.MainApplication
import com.rockwellstudios.mychat.di.module.AndroidModule
import com.rockwellstudios.mychat.di.module.AppModule
import com.rockwellstudios.mychat.di.module.NetworkModule
import com.rockwellstudios.mychat.di.module.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by user on 23.03.18.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        AndroidModule::class,
        NetworkModule::class,
        UtilModule::class))
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MainApplication): Builder

        fun build(): ApplicationComponent
    }
}