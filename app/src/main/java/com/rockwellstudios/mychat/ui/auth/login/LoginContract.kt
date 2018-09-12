package com.rockwellstudios.mychat.ui.auth.login

import com.rockwellstudios.mychat.base.BaseAuthView
import com.rockwellstudios.mychat.base.BasePresenter
import io.reactivex.Observable

/**
 * Created by user on 23.03.18.
 */
interface LoginContract {

    interface View : BaseAuthView{

        fun signInButtonClick(): Observable<Any>

        fun showSignUpScreen()

        fun moveToCoreScreen()

    }

    interface Presenter : BasePresenter
}