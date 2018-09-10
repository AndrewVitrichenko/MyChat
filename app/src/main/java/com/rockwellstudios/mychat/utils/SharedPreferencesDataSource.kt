package com.rockwellstudios.mychat.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(val preferences: SharedPreferences) :
        PreferenceDataSource {

    override fun getString(key: String, defaultValue: String) : String
            = preferences.getString(key,defaultValue)


    override fun putString(key: String, value: String)
            = preferences.edit().putString(key, value).apply()

}