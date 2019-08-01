package com.rockwellstudios.mychat.ui.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.rockwellstudios.mychat.entity.AuthEntities
import com.rockwellstudios.mychat.ui.auth.entities.UserState
import com.rockwellstudios.mychat.utils.SocketConnector
import org.json.JSONObject
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(val socketConnector: SocketConnector) : AuthDataSource {

    override fun registerUser(authBody: AuthEntities.AuthBody,callback: (e: UserState) -> Unit) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("userName", authBody.userName)
            jsonObject.put("email", authBody.email)
            jsonObject.put("password", authBody.password)
            socketConnector.initConnection()?.emit("userData", jsonObject)
        } catch (e: Exception) {
            callback.invoke(UserState.error(e.message.toString()))
        }
    }

    override fun login(authBody: AuthEntities.AuthBody, callback: (e: UserState) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(authBody.email, authBody.password)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        callback.invoke(UserState.error(task.exception?.message.toString()))
                    } else {
                        try {
                            val jsonObject = JSONObject()
                            jsonObject.put("email", authBody.email)
                            socketConnector.initConnection()?.emit("userInfo", jsonObject)
                        } catch (e: Exception) {
                            callback.invoke(UserState.error(e.message.toString()))
                        }
                    }
                }
    }


    override fun listenEvents(callback: (e: UserState) -> Unit) {
        socketConnector.initConnection()?.on("message") { args ->
            val jsonObject: JSONObject = args[0] as JSONObject
            try {
                val messageObject: JSONObject = jsonObject.getJSONObject("message")
                val message: String = messageObject.get("text") as String
                if (message == "Success") {
                    callback.invoke(UserState.success(message))
                } else{
                    callback.invoke(UserState.error(message))
                }
            } catch (e: Exception) {
                callback.invoke(UserState.error(e.message.toString()))
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
                val authBody = AuthEntities.AuthBody(userName, email, "", userPicture, token)
                if (email != "error") {
                    FirebaseAuth.getInstance().signInWithCustomToken(token)
                            .addOnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    callback.invoke(UserState.error(task.exception?.message.toString()))
                                } else {
                                    callback.invoke(UserState.success(authBody))
                                }
                            }
                } else {
                    callback.invoke(UserState.error(token))
                }
            } catch (e: Exception) {
                callback.invoke(UserState.error(e.message.toString()))
            }
        }
    }

    override fun stopEventListening() {
        socketConnector.initConnection()?.off("message")
        socketConnector.initConnection()?.off("token")
    }

}


