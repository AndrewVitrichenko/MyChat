package com.rockwellstudios.mychat.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rockwellstudios.mychat.R

/**
 * Created by Andrew on 26.12.2017.
 */
abstract class BaseActivity : AppCompatActivity() {


    fun showFragment(fragmentToShow: Fragment) {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentToShow.tag)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.tag)
                    .addToBackStack(fragmentToShow.tag)
                    .commitAllowingStateLoss()
        } else {
            supportFragmentManager.popBackStack(fragment.tag, 0)
        }
    }

    abstract fun getFragmentContainer() : Int


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}