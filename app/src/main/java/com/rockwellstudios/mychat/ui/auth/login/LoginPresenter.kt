package com.rockwellstudios.mychat.ui.auth.login

import android.util.Log
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.common.USER_EMAIL
import com.rockwellstudios.mychat.common.USER_NAME
import com.rockwellstudios.mychat.common.USER_PICTURE
import com.rockwellstudios.mychat.common.USER_TOKEN
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import com.rockwellstudios.mychat.utils.ResourceUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class LoginPresenter @Inject constructor(val view: LoginContract.View,
                                         val compositeDisposable: CompositeDisposable,
                                         val authSubject: PublishSubject<Any>,
                                         val authDataSource: AuthDataSource,
                                         val preferenceDataSource: PreferenceDataSource,
                                         val resourceUtil: ResourceUtil) : LoginContract.Presenter {


    override fun attach() {

        val emailStream = view.emailInputStream()
        val passwordStream = view.passwordInputStream()

        Observable.combineLatest(emailStream, passwordStream,
                BiFunction { email: String, password: String -> AuthEntities.AuthBody("", email, password, "", "") })
                .sample(view.signInButtonClick())
                .doOnNext { view.showLoading(true) }
                .doOnNext { authBody ->
                    authBody.apply {
                        if (email.trim().isEmpty() || password.trim().isEmpty()) {
                            view.apply {
                                showLoading(false)
                                showMessage(resourceUtil.getString(R.string.error_incorrect_data))
                            }
                        }
                    }
                }
                .filter { authBody -> !authBody.email.trim().isEmpty() && !authBody.password.trim().isEmpty() }
                .flatMap { authBody ->
                    authDataSource.login(authBody).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe()


        authSubject.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .doOnNext { view.showLoading(false) }
                .subscribe { message ->
                    if (message is String) {
                        view.showMessage(message)
                    } else {
                        val authBodyResponse: AuthEntities.AuthBody = message as AuthEntities.AuthBody
                        preferenceDataSource.apply {
                            putString(USER_TOKEN, authBodyResponse.token)
                            putString(USER_EMAIL, authBodyResponse.email)
                            putString(USER_NAME, authBodyResponse.userName)
                            putString(USER_PICTURE, authBodyResponse.userPicture)
                        }
                        view.moveToCoreScreen()
                    }
                }

        authDataSource.listenEvents()

        view.signUpButtonClick()
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe { view.showSignUpScreen() }

    }

    override fun detach() {
        authDataSource.stopEventListening()
        compositeDisposable.clear()
    }


}