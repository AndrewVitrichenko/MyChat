package com.rockwellstudios.mychat.ui.auth

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by user on 23.03.18.
 */
class AuthPresenter constructor(private val view : AuthContract.View,
                                private val sharedPreferences: SharedPreferences) : AuthContract.Presenter {

    private val USER_ID = "user_id"

    override fun subscribe() {
        val userId = sharedPreferences.getString(USER_ID,"")
        userId?.let {
            if (it.isEmpty()){
                view.showLoginScreen()
            } else{
                view.showMainScreen()
            }
        }
    }
}