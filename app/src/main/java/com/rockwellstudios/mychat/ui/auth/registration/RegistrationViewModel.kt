package com.rockwellstudios.mychat.ui.auth.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.common.Status
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.ui.auth.entities.UserState
import com.rockwellstudios.mychat.utils.ResourceUtil
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val authDataSource: AuthDataSource,
                                                private val resourceUtil: ResourceUtil) : ViewModel() {

    private val registrationStateLiveData: MutableLiveData<UserState> = MutableLiveData()

    fun getRegistrationState(): MutableLiveData<UserState> = registrationStateLiveData

    init {
        authDataSource.listenEvents { userState -> registrationStateLiveData.value = userState }
    }

    fun onRegistrationButtonClicked(userName: String, email: String, password: String) {
        if (userName.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            registrationStateLiveData.value = UserState.error(resourceUtil.getString(R.string.error_incorrect_data))
        } else {
            registrationStateLiveData.value = UserState.loading()
            val authBody = AuthEntities.AuthBody(userName, email, password, "", "")
            authDataSource.registerUser(authBody) { registrationStateLiveData.value = it }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authDataSource.stopEventListening()
    }

}