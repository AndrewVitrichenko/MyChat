package com.rockwellstudios.mychat.ui.main.friends.find.entity

data class User(var userName : String,var email : String,var userPicture : String,var hasLoggedIn : Boolean){
    constructor() : this("", "",
            "",false)
}