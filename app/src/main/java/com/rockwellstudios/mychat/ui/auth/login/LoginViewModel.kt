package com.rockwellstudios.mychat.ui.auth.login

import android.arch.lifecycle.ViewModel
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import javax.inject.Inject

class LoginViewModel @Inject constructor(val dataSource: AuthDataSource) : ViewModel() {
}