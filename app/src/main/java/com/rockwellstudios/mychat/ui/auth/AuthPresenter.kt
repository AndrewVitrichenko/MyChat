package com.rockwellstudios.mychat.ui.auth

import com.google.firebase.auth.FirebaseAuth
import com.rockwellstudios.mychat.common.USER_TOKEN
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class AuthPresenter @Inject constructor(val view : AuthContract.View,
                                            val preferenceDataSource: PreferenceDataSource)
    : AuthContract.Presenter {

    override fun attach() {
//        val userId = preferenceDataSource.getString(USER_TOKEN,"")
//        userId.let {
//            if (it.isEmpty()){
                view.showLoginScreen()
//            } else{
//                view.showMainScreen()
//            }
//        }
    }

    override fun detach() {

    }
}