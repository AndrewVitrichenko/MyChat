package com.rockwellstudios.mychat.ui.auth.registration

import android.content.SharedPreferences
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationPresenter @Inject constructor( val view: RegistrationContract.View,
                                         val sharedPreferencesDataSource: SharedPreferencesDataSource): RegistrationContract.Presenter {

    override fun onSignInButtonClick(authBody: AuthEntities.AuthBody) {
        val jsonObject = JSONObject()
        jsonObject.put("email",authBody.email)
        jsonObject.put("password",authBody.password)
        view.sendAuthRequest(jsonObject)
    }


    override fun attach() {

    }

    override fun detach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun registerUser (authEntity : AuthEntities.AuthBody){
    }




}