package com.rockwellstudios.mychat.di.component

import com.rockwellstudios.mychat.MainApplication
import com.rockwellstudios.mychat.di.module.*
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
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        ViewModelFactoryModule::class,
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