package com.rockwellstudios.mychat.ui.auth.registration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseFragment
import com.rockwellstudios.mychat.entity.AuthEntities
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        try {
//            socket = IO.socket(IP_LOCAL_HOST)
//            socket.connect()
//        } catch (e: Exception) {
//
//        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignIn.setOnClickListener({
            if (!editTextEmail.text.isEmpty() && !editTextPassword.text.isEmpty()) {
                val authBody = AuthEntities.AuthBody(email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString())
                presenter.onSignInButtonClick(authBody)
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun sendAuthRequest(jsonObject: JSONObject) {
        socket?.emit("userData", jsonObject)
    }

    override fun showLoading(loading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}