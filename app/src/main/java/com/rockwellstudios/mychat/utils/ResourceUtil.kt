package com.rockwellstudios.mychat.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceUtil @Inject constructor(val context: Context) {

    fun getString(@StringRes stringResource : Int) : String = context.getString(stringResource)

}