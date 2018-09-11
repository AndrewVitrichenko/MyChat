package com.rockwellstudios.mychat.ui.auth.registration

import android.content.SharedPreferences
import android.util.Log
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.ResourceUtil
import com.rockwellstudios.mychat.utils.SharedPreferencesDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationPresenter @Inject constructor(val view: RegistrationContract.View,
                                                val compositeDisposable: CompositeDisposable,
                                                val authDataSource: AuthDataSource,
                                                val resourceUtil: ResourceUtil) : RegistrationContract.Presenter {


    override fun attach() {
        Observables.combineLatest(
                view.emailInputStream(),
                view.passwordInputStream()
        ) { email, password -> AuthEntities.AuthBody(email, password) }
                .sample(view.signUpButtonClick())
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
                .flatMapCompletable { authBody ->
                    authDataSource.registerUser(authBody)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe({
                    view.apply {
                        showLoading(false)
                        moveToCoreScreen()
                    }
                }, { error ->
                    view.showLoading(false)
                    Log.e("TAG", "{$error.message}")
                })
    }

    override fun detach() {
        compositeDisposable?.clear()
    }

}