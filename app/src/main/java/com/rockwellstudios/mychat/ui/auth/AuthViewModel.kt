package com.rockwellstudios.mychat.ui.auth

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rockwellstudios.mychat.ui.auth.data.AuthDataSource
import com.rockwellstudios.mychat.utils.PreferenceDataSource
import javax.inject.Inject

class AuthViewModel @Inject constructor(val preferenceDataSource: PreferenceDataSource) : ViewModel() {

    private val mutableLiveData : MutableLiveData<>

}