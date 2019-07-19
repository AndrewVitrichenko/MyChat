package com.rockwellstudios.mychat.base

import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseBottomBarActivity : BaseActivity(),BaseBottomBarView {

    abstract fun getBottomBar() : BottomNavigationView

    override fun setBottomItemChecked(itemId: Int) {
        val menu = getBottomBar().menu
        var i = 0
        val size = menu.size()
        while (i < size) {
            val item = menu.getItem(i)
            if (item.itemId == itemId){
                item.isChecked = true
            }
            i++
        }
    }

}