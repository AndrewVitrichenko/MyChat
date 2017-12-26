package com.rockwellstudios.mychat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import butterknife.BindView
import com.rockwellstudios.mychat.common.BaseActivity
import com.rockwellstudios.mychat.util.Constants
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class MainActivity : BaseActivity() {

    private var mSocket: Socket? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            mSocket = IO.socket(Constants.IP_LOCAL_HOST)
        } catch (e: URISyntaxException) {
            Log.e(this.javaClass.simpleName, e.message)
        }
        mSocket?.connect()
    }


    override fun onDestroy() {
        mSocket?.disconnect()
        super.onDestroy()
    }
}
