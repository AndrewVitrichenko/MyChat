package com.rockwellstudios.mychat.ui.auth.registration

import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import dagger.Module
import dagger.Provides

@Module
abstract class RegistrationModule {

    @Provides
    fun provideRegistrationViewModel(dataSource: AuthDataSource) : RegistrationViewModel{
        return RegistrationViewModel(dataSource)
    }
}