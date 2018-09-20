package com.rockwellstudios.mychat.ui.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.utils.SocketConnector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.socket.emitter.Emitter
import org.json.JSONObject
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(val socketConnector: SocketConnector,
                                               val authSubject: PublishSubject<Any>) : AuthDataSource {

    override fun registerUser(authBody: AuthEntities.AuthBody) {
        try {
            val jsonObject = JSONObject()
            jsonObject.apply {
                put("userName", authBody.userName)
                put("email", authBody.email)
                put("password", authBody.password)
                socketConnector.initConnection()?.emit("userData", jsonObject)
            }
        } catch (e: Exception) {
        }
    }

    override fun login(authBody: AuthEntities.AuthBody) : Observable<AuthEntities.AuthBody> {
        return Observable.just(authBody)
                .doOnNext{ authBody ->  FirebaseAuth.getInstance().signInWithEmailAndPassword(authBody.email, authBody.password)
                        .addOnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                authSubject.onNext(task.exception?.message.toString())
                            } else {
                                try {
                                    val jsonObject = JSONObject()
                                    jsonObject.put("email", authBody.email)
                                    socketConnector.initConnection()?.emit("userInfo", jsonObject)
                                } catch (e: Exception) {
                                    authSubject.onNext(e.message.toString())
                                }
                            }
                        }
                }
    }


    override fun listenEvents() {
        socketConnector.initConnection()?.on("message") { args ->
            val jsonObject: JSONObject = args[0] as JSONObject
            try {
                val messageObject: JSONObject = jsonObject.getJSONObject("message")
                val message: String = messageObject.get("text") as String
                authSubject.onNext(message)
            } catch (e: Exception) {
                authSubject.onNext(e.message.toString())
            }
        }
        socketConnector.initConnection()?.on("token") { args ->
            val jsonObject: JSONObject = args[0] as JSONObject
            try {
                val messageObject: JSONObject = jsonObject.getJSONObject("token")
                val token: String = messageObject.get("authToken") as String
                val email: String = messageObject.get("email") as String
                val userPicture: String = messageObject.get("userPicture") as String
                val userName: String = messageObject.get("userName") as String
                val authBody = AuthEntities.AuthBody(userName,email,"",userPicture,token)
                if (email != "error"){
                    FirebaseAuth.getInstance().signInWithCustomToken(token)
                            .addOnCompleteListener { task ->
                                if (!task.isSuccessful){
                                    authSubject.onNext(task.exception?.message.toString())
                                } else{
                                    authSubject.onNext(authBody)
                                }
                            }
                } else{
                    authSubject.onNext(token)
                }
            } catch (e: Exception) {
                authSubject.onNext(e.message.toString())
            }
        }
    }

    override fun stopEventListening() {
        socketConnector.initConnection()?.off("message")
        socketConnector.initConnection()?.off("token")
    }

}


