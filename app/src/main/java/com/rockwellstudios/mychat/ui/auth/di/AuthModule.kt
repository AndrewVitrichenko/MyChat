package com.rockwellstudios.mychat.ui.auth.di

import android.content.SharedPreferences
import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.AuthContract
import com.rockwellstudios.mychat.ui.auth.AuthPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by user on 23.03.18.
 */

@Module
abstract class AuthModule {

    @Binds
    abstract fun bindView(authActivity: AuthActivity) : AuthContract.View

    @Provides
    fun provideAuthPresenter(view: AuthContract.View,
                             sharedPreferences: SharedPreferences): AuthContract.Presenter {
        return AuthPresenter(view, sharedPreferences)
    }

}