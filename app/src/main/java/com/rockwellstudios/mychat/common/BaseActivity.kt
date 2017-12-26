package com.rockwellstudios.mychat.common

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rockwellstudios.mychat.R

/**
 * Created by Andrew on 26.12.2017.
 */
open class BaseActivity : AppCompatActivity() {


    fun showFragment(fragmentToShow: Fragment) {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentToShow.tag)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragmentToShow, fragmentToShow.tag)
                    .addToBackStack(fragmentToShow.tag)
                    .commitAllowingStateLoss()
        } else {
            supportFragmentManager.popBackStack(fragment.tag, 0)
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}