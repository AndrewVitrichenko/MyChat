package com.rockwellstudios.mychat.ui.auth.registration

import android.content.SharedPreferences
import com.rockwellstudios.mychat.entity.AuthEntities
import io.socket.client.Socket
import org.json.JSONObject

/**
 * Created by user on 23.03.18.
 */
class RegistrationPresenter constructor(private val view: RegistrationContract.View,
                                        private val sharedPreferences: SharedPreferences): RegistrationContract.Presenter {
    override fun onSignInButtonClick(authBody: AuthEntities.AuthBody) {
        val jsonObject = JSONObject()
        jsonObject.put("email",authBody.email)
        jsonObject.put("password",authBody.password)
        view.sendAuthRequest(jsonObject)
    }


    override fun subscribe() {

    }


    fun registerUser (authEntity : AuthEntities.AuthBody){
    }




}