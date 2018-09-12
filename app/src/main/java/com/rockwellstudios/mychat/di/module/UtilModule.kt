package com.rockwellstudios.mychat.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import com.rockwellstudios.mychat.utils.ResourceUtil
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class UtilModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSharedPreferencesDataSource(sharedPreferences: SharedPreferences): PreferenceDataSource
            = SharedPreferencesDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideResourceUtil(context: Context) : ResourceUtil = ResourceUtil(context)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}