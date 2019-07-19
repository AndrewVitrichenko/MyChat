package com.rockwellstudios.mychat.ui.auth.entities

import com.rockwellstudios.mychat.common.Status
import com.rockwellstudios.mychat.entity.AuthEntities

data class UserState(var status: Int, var message: String?, var  authBody: AuthEntities.AuthBody?) {

    companion object {
        fun loading(): UserState = UserState(Status.LOADING, null, null)
        fun error(message: String): UserState = UserState(Status.ERROR, message, null)
        fun success(authBody: AuthEntities.AuthBody): UserState = UserState(Status.SUCCESS, null, authBody)
    }
}