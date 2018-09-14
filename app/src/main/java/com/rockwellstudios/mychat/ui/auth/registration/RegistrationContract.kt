package com.rockwellstudios.mychat.ui.auth.registration

import com.rockwellstudios.mychat.base.BaseAuthView
import com.rockwellstudios.mychat.base.BasePresenter

/**
 * Created by user on 23.03.18.
 */
interface RegistrationContract {

    interface View : BaseAuthView {

        fun moveToLogin()
    }


    interface Presenter : BasePresenter
}