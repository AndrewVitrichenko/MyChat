package com.rockwellstudios.mychat.di.module

import androidx.lifecycle.ViewModelProvider
import com.rockwellstudios.mychat.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}