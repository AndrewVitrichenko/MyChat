package com.rockwellstudios.mychat.ui.auth.registration

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView
import com.rockwellstudios.mychat.entity.AuthEntities
import org.json.JSONObject

/**
 * Created by user on 23.03.18.
 */
interface RegistrationContract {

    interface Model{

    }

    interface View : BaseView{
        fun sendAuthRequest(jsonObject: JSONObject)

    }

    interface Presenter : BasePresenter{
        fun onSignInButtonClick(authBody: AuthEntities.AuthBody)

    }
}