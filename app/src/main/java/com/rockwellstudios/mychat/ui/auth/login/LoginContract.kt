package com.rockwellstudios.mychat.ui.auth.login

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView
import com.rockwellstudios.mychat.entity.AuthEntities
import io.reactivex.Observable
import io.reactivex.ObservableSource

/**
 * Created by user on 23.03.18.
 */
interface LoginContract {

    interface Model{

    }

    interface View : BaseView{
        fun emailInputStream(): Observable<String>

        fun passwordInputStream(): Observable<String>

        fun signInButtonClick(): Observable<Any>

        fun signUpButtonClick(): Observable<Any>

        fun moveToCoreScreen()

        fun showSignUpScreen()

    }

    interface Presenter : BasePresenter{

    }
}