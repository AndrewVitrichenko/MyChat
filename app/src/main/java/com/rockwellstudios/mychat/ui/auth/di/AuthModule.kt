package com.rockwellstudios.mychat.ui.auth.di

import com.rockwellstudios.mychat.ui.auth.AuthViewModel
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by user on 23.03.18.
 */

@Module
abstract class AuthModule {

    @Provides
    fun provideAuthViewModel(preferenceDataSource: PreferenceDataSource) : AuthViewModel{
        return AuthViewModel(preferenceDataSource)
    }

//    @Binds
//    abstract fun bindAuthView(authActivity: AuthActivity): AuthContract.View
//
//    @Binds
//    abstract fun bindAuthPresenter(authPresenter: AuthPresenter): AuthContract.Presenter
//
//    @Binds
//    abstract fun bindRegistrationView(registrationFragment: RegistrationFragment): RegistrationContract.View
//
//    @Binds
//    abstract fun bindRegistrationPresenter(registrationPresenter: RegistrationPresenter): RegistrationContract.Presenter
//
//    @Binds
//    abstract fun bindLoginView(loginFragment: LoginFragment): LoginContract.View
//
//    @Binds
//    abstract fun bindLoginPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

}
