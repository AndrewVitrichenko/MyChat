package com.rockwellstudios.mychat.base

import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Andrew on 26.12.2017.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {


    fun showFragment(fragmentToShow: BaseFragment, addToBackStack : Boolean) {
        supportFragmentManager?.let {
            if (!addToBackStack){
                it.beginTransaction()
                        .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                        .commitAllowingStateLoss()
            } else {
                val fragment = it.findFragmentByTag(fragmentToShow.getFragmentTag())
                if (fragment == null) {
                    it.beginTransaction()
                            .replace(getFragmentContainer(), fragmentToShow, fragmentToShow.getFragmentTag())
                            .addToBackStack(fragmentToShow.getFragmentTag())
                            .commitAllowingStateLoss()
                } else {
                    val castedFragment = fragment as BaseFragment
                    it.popBackStack(castedFragment.getFragmentTag(), 0)
                }
            }
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