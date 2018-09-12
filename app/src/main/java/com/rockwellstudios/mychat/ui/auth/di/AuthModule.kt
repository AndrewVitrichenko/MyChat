package com.rockwellstudios.mychat.ui.auth.di

import com.rockwellstudios.mychat.ui.auth.AuthActivity
import com.rockwellstudios.mychat.ui.auth.AuthContract
import com.rockwellstudios.mychat.ui.auth.AuthPresenter
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.ui.auth.data.AuthRemoteDataSource
import com.rockwellstudios.mychat.ui.auth.login.LoginContract
import com.rockwellstudios.mychat.ui.auth.login.LoginFragment
import com.rockwellstudios.mychat.ui.auth.login.LoginPresenter
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationContract
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationFragment
import com.rockwellstudios.mychat.ui.auth.registration.RegistrationPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by user on 23.03.18.
 */

@Module
abstract class AuthModule {

    @Binds
    abstract fun bindDataSource(authRemoteDataSource: AuthRemoteDataSource) : AuthDataSource

    @Binds
    abstract fun bindAuthView(authActivity: AuthActivity): AuthContract.View

    @Binds
    abstract fun bindAuthPresenter(authPresenter: AuthPresenter): AuthContract.Presenter

    @Binds
    abstract fun bindRegistrationView(registrationFragment: RegistrationFragment): RegistrationContract.View

    @Binds
    abstract fun bindRegistrationPresenter(registrationPresenter: RegistrationPresenter): RegistrationContract.Presenter

    @Binds
    abstract fun bindLoginView(loginFragment: LoginFragment): LoginContract.View

    @Binds
    abstract fun bindLoginPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

}
