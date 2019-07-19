package com.rockwellstudios.mychat.ui.auth.data

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AuthDataModule {

    @Singleton
    @Binds
    abstract fun bindDataSource(authRemoteDataSource: AuthRemoteDataSource) : AuthDataSource
}