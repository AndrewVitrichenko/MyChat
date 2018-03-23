package com.rockwellstudios.mychat.ui.auth.registration

import android.content.SharedPreferences
import com.rockwellstudios.mychat.entity.AuthEntities

/**
 * Created by user on 23.03.18.
 */
class RegistrationPresenter constructor(private val view: RegistrationContract.View,
                                        private val sharedPreferences: SharedPreferences): RegistrationContract.Presenter {


    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun registerUser (authEntity : AuthEntities.AuthBody){

    }




}