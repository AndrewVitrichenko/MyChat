package com.rockwellstudios.mychat.ui.auth.data

import com.rockwellstudios.mychat.common.IP_LOCAL_HOST
import com.rockwellstudios.mychat.entity.AuthEntities
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import java.net.URISyntaxException

class AuthRemoteDataSource : AuthDataSource {

    private var mSocket: Socket? = null

    init {
//        mSocket =
//                try {
//                    IO.socket(IP_LOCAL_HOST)
//                } catch (e: URISyntaxException) {
//                    null
//                }
//        mSocket?.connect()
//        mSocket.let { it ->
//            it?.on(Socket.EVENT_CONNECT) {
//                connected = true
//            }
//            it?.on(Socket.EVENT_CONNECT_ERROR) {
//                connected = false
//            }
//            it?.on(Socket.EVENT_DISCONNECT) {
//                connected = false
//            }
//        }
    }

    override fun initConnection() {
        mSocket =
                try {
                    IO.socket(IP_LOCAL_HOST)
                } catch (e: URISyntaxException) {
                    null
                }
        mSocket?.connect()
    }

    override fun registerUser(authBody: AuthEntities.AuthBody): Completable {
        return Completable.fromAction {
            try {
                mSocket?.emit("userData", authBody)
                Completable.complete()
            } catch (e: Exception) {
                Completable.error(e)
            }
        }
    }

    override fun login(authBody: AuthEntities.AuthBody): Completable {
        return Completable.fromAction {
            try {
                mSocket?.emit("userData", authBody)
                Completable.complete()
            } catch (e: Exception) {
                Completable.error(e)
            }
        }
    }

    override fun closeConnection(){
        mSocket?.disconnect()
    }


}


