package com.rockwellstudios.mychat.ui.auth.data

import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.utils.SocketConnector
import io.reactivex.subjects.PublishSubject
import io.socket.emitter.Emitter
import org.json.JSONObject
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(val socketConnector: SocketConnector): AuthDataSource {

    var listener: Emitter.Listener? = null

    override fun registerUser(authBody: AuthEntities.AuthBody){
        try {
            val jsonObject = JSONObject()
            jsonObject.put("userName",authBody.userName)
            jsonObject.put("email",authBody.email)
            jsonObject.put("password",authBody.password)
            socketConnector.initConnection()?.emit("userData", jsonObject)
        } catch (e: Exception) {
        }
    }

    override fun login(authBody: AuthEntities.AuthBody){
        try {
            val jsonObject = JSONObject()
            jsonObject.put("userName",authBody.userName)
            jsonObject.put("email",authBody.email)
            jsonObject.put("password",authBody.password)
            socketConnector.initConnection()?.emit("userData", jsonObject)
        } catch (e: Exception) {
        }
    }


    override fun listenEvents(authSubject: PublishSubject<String>){
        listener = Emitter.Listener { args ->
            val jsonObject: JSONObject = args[0] as JSONObject
            try {
                val messageObject : JSONObject = jsonObject.getJSONObject("message")
                val message : String = messageObject.get("text") as String
                authSubject.onNext(message)
            }catch (e : Exception){
                authSubject.onNext(e.message.toString())
            }
        }
        socketConnector.initConnection()?.on("message",listener)
    }

    override fun stopEventListening() {
        socketConnector.initConnection()?.off("message")
        listener = null
    }

}


