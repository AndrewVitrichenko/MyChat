package com.rockwellstudios.mychat.ui.auth.registration

import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.ResourceUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationPresenter @Inject constructor(val view: RegistrationContract.View,
                                                val compositeDisposable: CompositeDisposable,
                                                val authDataSource: AuthDataSource,
                                                val authSubject: PublishSubject<Any>,
                                                val resourceUtil: ResourceUtil) : RegistrationContract.Presenter {


    override fun attach() {
//        Observables.combineLatest(
//                view.userNameInputStream(),
//                view.emailInputStream(),
//                view.passwordInputStream()
//        ) { userName, email, password -> AuthEntities.AuthBody(userName, email, password, "","") }
//                .sample(view.signUpButtonClick())
//                .doOnNext { view.showLoading(true) }
//                .doOnNext { authBody ->
//                    authBody.apply {
//                        if (userName.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
//                            view.apply {
//                                showLoading(false)
//                                showMessage(resourceUtil.getString(R.string.error_incorrect_data))
//                            }
//                        }
//                    }
//                }
//                .filter { authBody ->
//                    !authBody.userName.trim().isEmpty() &&
//                            !authBody.email.trim().isEmpty() && !authBody.password.trim().isEmpty()
//                }
//                .observeOn(Schedulers.io())
//                .doOnNext { authBody -> authDataSource.registerUser(authBody) }
//                .doOnSubscribe { compositeDisposable.add(it) }
//                .subscribe()
//
//        authSubject.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { compositeDisposable.add(it) }
//                .doOnNext { view.showLoading(false) }
//                .subscribe { message ->
//                    if (message is String) {
//                        when (message) {
//                            "Success" -> view.moveToLogin()
//                            else -> view.showMessage(message)
//                        }
//                    }
//                }

//        authDataSource.listenEvents()
    }

    override fun detach() {
        authDataSource.stopEventListening()
        compositeDisposable.clear()
    }

}