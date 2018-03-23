package com.rockwellstudios.mychat.ui.auth.login

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView
import com.rockwellstudios.mychat.entity.AuthEntities

/**
 * Created by user on 23.03.18.
 */
interface LoginContract {

    interface Model{

    }

    interface View : BaseView{

    }

    interface Presenter : BasePresenter{

        fun onSignInButtonClick(authBody: AuthEntities.AuthBody)

    }
}