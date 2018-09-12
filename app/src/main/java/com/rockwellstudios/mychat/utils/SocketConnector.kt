package com.rockwellstudios.mychat.utils

import com.rockwellstudios.mychat.common.IP_LOCAL_HOST
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import javax.inject.Inject

class SocketConnector @Inject constructor(){

    private var mSocket : Socket? = null

    fun initConnection() : Socket? {
        if (mSocket == null) {
            mSocket =
                    try {
                        IO.socket(IP_LOCAL_HOST)
                    } catch (e: URISyntaxException) {
                        null
                    }
            mSocket?.connect()
        }
        return mSocket
    }

    fun closeConnection(){
        mSocket?.off()
        mSocket?.disconnect()
        mSocket = null
    }


}