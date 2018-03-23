package com.rockwellstudios.mychat.ui.login

import android.os.Bundle
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.base.BaseActivity
import com.rockwellstudios.mychat.server.IP_LOCAL_HOST
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

/**
 * Created by Andrew on 25.02.2018.
 */
class LoginActivity : BaseActivity() {

    private var mSocket: Socket? = null

    override fun getFragmentContainer() = R.id.activity_login_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSocket =
                try {
                    IO.socket(IP_LOCAL_HOST)
                } catch (e: URISyntaxException) {
                    null
                }
        mSocket?.connect()

    }


    override fun onDestroy() {
        mSocket?.disconnect()
        super.onDestroy()
    }
}