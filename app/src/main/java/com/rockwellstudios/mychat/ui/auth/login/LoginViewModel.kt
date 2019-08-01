package com.rockwellstudios.mychat.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.common.*
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.ui.auth.entities.UserState
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import com.rockwellstudios.mychat.utils.ResourceUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val authDataSource: AuthDataSource,
                                         private val preferenceDataSource: PreferenceDataSource,
                                         private val resourceUtil: ResourceUtil) : ViewModel() {

    private val loginStateLiveData: MutableLiveData<UserState> = MutableLiveData()

    init {
        authDataSource.listenEvents { userState ->
            if (userState.status == Status.SUCCESS) {
                preferenceDataSource.apply {
                    putString(USER_TOKEN, userState.authBody!!.token)
                    putString(USER_EMAIL, userState.authBody!!.email)
                    putString(USER_NAME, userState.authBody!!.userName)
                    putString(USER_PICTURE, userState.authBody!!.userPicture)
                }
            }
            loginStateLiveData.value = userState
        }
    }

    fun onSignInButtonClick(email: String, password: String) {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            loginStateLiveData.value = UserState.error(resourceUtil.getString(R.string.error_incorrect_data))
        } else {
            loginStateLiveData.value = UserState.loading()
            val loginBody = AuthEntities.AuthBody("", email, password, "", "")
            authDataSource.login(loginBody) { error -> loginStateLiveData.value = error }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authDataSource.stopEventListening()
    }

    fun getLoginState() : MutableLiveData<UserState> = loginStateLiveData
}