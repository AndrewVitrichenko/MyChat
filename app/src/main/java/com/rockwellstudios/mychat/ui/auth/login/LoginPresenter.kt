package com.rockwellstudios.mychat.ui.auth.login

import android.util.Log
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.utils.ResourceUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class LoginPresenter @Inject constructor(val view: LoginContract.View,
                                         val compositeDisposable: CompositeDisposable,
                                         val resourceUtil: ResourceUtil) : LoginContract.Presenter {


    override fun attach() {
        Observables.combineLatest(
                view.emailInputStream(),
                view.passwordInputStream()
        ) {email, password -> AuthEntities.AuthBody("",email, password) }
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
                .doOnSubscribe { compositeDisposable.add(it) }
//                .flatMap{ authBody ->  }
                .subscribe({
                    view.apply {
                        showLoading(false)
                        moveToCoreScreen()
                    }
                }, { error ->
                            view.showLoading(false)
                            Log.e("TAG", "{$error.message}")
                        })

        view.signUpButtonClick()
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe { view.showSignUpScreen() }

    }

    override fun detach() {
        compositeDisposable.clear()
    }


}