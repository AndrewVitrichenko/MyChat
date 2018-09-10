package com.rockwellstudios.mychat.ui.auth

import android.content.Context
import android.content.SharedPreferences
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class AuthPresenter @Inject constructor(val view : AuthContract.View,
                                            val sharedPreferencesDataSource: SharedPreferencesDataSource)
    : AuthContract.Presenter {

    private val USER_ID = "user_id"

    override fun attach() {
        val userId = sharedPreferencesDataSource.getString(USER_ID,"")
        userId?.let {
            if (it.isEmpty()){
                view?.showLoginScreen()
            } else{
                view?.showMainScreen()
            }
        }
    }

    override fun detach() {

    }
}