package com.rockwellstudios.mychat.utils

interface PreferenceDataSource {

    fun putString(key : String, value: String)

    fun getString(key : String, defaultValue: String) : String

}