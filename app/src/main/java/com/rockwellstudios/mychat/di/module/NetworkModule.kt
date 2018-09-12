package com.rockwellstudios.mychat.di.module

import com.rockwellstudios.mychat.utils.SocketConnector
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSocketConnector() = SocketConnector()


    @Provides
    @Singleton
    fun providePublishSubject() = PublishSubject.create<String>()
}