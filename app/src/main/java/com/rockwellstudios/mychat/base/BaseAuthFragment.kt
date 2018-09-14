package com.rockwellstudios.mychat.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_auth.*

abstract class BaseAuthFragment : BaseFragment(), BaseAuthView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutContainer(),container,false)
    }

    abstract fun getLayoutContainer() : Int

    override fun userNameInputStream(): Observable<String>  = textChanges(editTextUserName)

    override fun emailInputStream(): Observable<String>  = textChanges(editTextEmail)

    override fun passwordInputStream(): Observable<String>  = textChanges(editTextPassword)

    override fun signUpButtonClick(): Observable<Any> = viewClick(btnSignUp)


    override fun showLoading(loading: Boolean) {
        progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
    }
}