package com.rockwellstudios.mychat.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rockwellstudios.mychat.MainApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by user on 23.03.18.
 */
@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindContext(application: MainApplication):Context

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) = PreferenceManager
            .getDefaultSharedPreferences(context)

}