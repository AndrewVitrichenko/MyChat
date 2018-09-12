package com.rockwellstudios.mychat.ui.auth

import com.rockwellstudios.mychat.base.BasePresenter

/**
 * Created by user on 23.03.18.
 */
interface AuthContract {

    interface View {
        fun showLoginScreen()

        fun showMainScreen()
    }

    interface Presenter : BasePresenter {

    }
}