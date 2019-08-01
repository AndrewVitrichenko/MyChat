package com.rockwellstudios.mychat.ui.auth.di

import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.di.ViewModelKey
import com.rockwellstudios.mychat.ui.auth.AuthViewModel
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.ui.auth.data.AuthRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by user on 23.03.18.
 */

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindMyViewModel(myViewModel: AuthViewModel): ViewModel

}