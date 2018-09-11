package com.rockwellstudios.mychat.ui.auth.data

import com.rockwellstudios.mychat.entity.AuthEntities
import io.reactivex.Completable

interface AuthDataSource {

    fun initConnection()

    fun closeConnection()

    fun registerUser(authBody: AuthEntities.AuthBody): Completable

    fun login(authBody: AuthEntities.AuthBody): Completable
}