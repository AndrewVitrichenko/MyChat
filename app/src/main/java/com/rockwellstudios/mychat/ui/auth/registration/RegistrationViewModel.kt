package com.rockwellstudios.mychat.ui.auth.registration

import android.arch.lifecycle.ViewModel
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val dataSource:AuthDataSource) : ViewModel() {
}