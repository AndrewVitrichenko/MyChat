package com.rockwellstudios.mychat.ui.auth.registration

import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.di.ViewModelKey
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class RegistrationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindMyViewModel(myViewModel: RegistrationViewModel): ViewModel
}