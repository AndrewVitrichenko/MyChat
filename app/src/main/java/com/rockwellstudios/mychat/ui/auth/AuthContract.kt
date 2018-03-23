package com.rockwellstudios.mychat.ui.auth

import com.rockwellstudios.mychat.base.BasePresenter
import com.rockwellstudios.mychat.base.BaseView

/**
 * Created by user on 23.03.18.
 */
interface AuthContract {

    interface View : BaseView {
        fun showLoginScreen()
        fun showMainScreen()
    }

    interface Presenter : BasePresenter {

    }
}