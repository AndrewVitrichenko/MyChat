package com.rockwellstudios.mychat.ui.auth.data

import com.rockwellstudios.mychat.entity.AuthEntities
import io.reactivex.subjects.PublishSubject

interface AuthDataSource {

    fun registerUser(authBody: AuthEntities.AuthBody)

    fun login(authBody: AuthEntities.AuthBody)

    fun listenEvents(authSubject: PublishSubject<String>)

    fun stopEventListening()

}