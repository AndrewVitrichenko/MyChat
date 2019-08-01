package com.rockwellstudios.mychat.ui.auth.data

import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.entities.UserState

interface AuthDataSource {

    fun registerUser(authBody: AuthEntities.AuthBody,callback: (e: UserState) -> Unit)

    fun login(authBody: AuthEntities.AuthBody, callback: (e: UserState) -> Unit)

    fun listenEvents(callback: (e: UserState) -> Unit)

    fun stopEventListening()

}