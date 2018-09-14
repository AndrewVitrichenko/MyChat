package com.rockwellstudios.mychat.base

import io.reactivex.Observable

interface BaseAuthView : BaseView {

    fun userNameInputStream(): Observable<String>

    fun emailInputStream(): Observable<String>

    fun passwordInputStream(): Observable<String>

    fun signUpButtonClick(): Observable<Any>

}