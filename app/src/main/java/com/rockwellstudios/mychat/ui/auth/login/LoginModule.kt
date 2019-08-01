package com.rockwellstudios.mychat.ui.auth.login

import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.di.ViewModelKey
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMyViewModel(myViewModel: LoginViewModel): ViewModel
}