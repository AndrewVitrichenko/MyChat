package com.rockwellstudios.mychat.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.common.USER_TOKEN
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val preferenceDataSource: PreferenceDataSource) : ViewModel() {

    private val authState: MutableLiveData<Boolean> = MutableLiveData()

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        val userId = preferenceDataSource.getString(USER_TOKEN, "")
        userId.let {
            authState.value = it.isEmpty()
        }
    }

    fun getAuthState(): MutableLiveData<Boolean> = authState

}