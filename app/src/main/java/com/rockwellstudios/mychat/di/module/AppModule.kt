package com.rockwellstudios.mychat.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rockwellstudios.mychat.MainApplication
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
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
    abstract fun bindContext(application: MainApplication): Context

}