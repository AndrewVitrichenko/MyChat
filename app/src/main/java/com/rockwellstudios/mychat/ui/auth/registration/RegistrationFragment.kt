package com.rockwellstudios.mychat.ui.auth.registration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.AuthContract
import io.reactivex.Observable
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by user on 23.03.18.
 */
class RegistrationFragment : BaseFragment(), RegistrationContract.View {

    private lateinit var socket: Socket

    @Inject
    lateinit var presenter: RegistrationContract.Presenter

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach()
    }

    override fun emailInputStream(): Observable<String>  = RxTextView.textChanges(editTextEmail)
            .map {it.toString()}

    override fun passwordInputStream(): Observable<String> = RxTextView.textChanges(editTextPassword)
            .map {it.toString()}


    override fun signUpButtonClick(): Observable<Any> = RxView.clicks(btnSignUp)

    override fun moveToCoreScreen() {
        activity?.let {
            val authView : AuthContract.View = it as AuthContract.View
            authView.showMainScreen()
        }
    }


//    override fun sendAuthRequest(jsonObject: JSONObject) {
//        socket?.emit("userData", jsonObject)
//    }

    override fun showLoading(loading: Boolean) {
        progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }



}