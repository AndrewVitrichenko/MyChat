package com.rockwellstudios.mychat.ui.auth.login

import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import dagger.Module
import dagger.Provides

@Module
abstract class LoginModule {

    @Provides
    fun provideLoginViewModel(dataSource: AuthDataSource) : LoginViewModel{
        return LoginViewModel(dataSource)
    }
}