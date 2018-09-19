package com.rockwellstudios.mychat.common

fun encodeEmail(email: String) : String = email.replace(".",",")