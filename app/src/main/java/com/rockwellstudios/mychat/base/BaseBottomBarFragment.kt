package com.rockwellstudios.mychat.base

abstract class BaseBottomBarFragment : BaseFragment() {

    abstract fun getBottomBarItem() : Int

    override fun onResume() {
        super.onResume()
        activity?.let {
            val bottomBarView : BaseBottomBarView = it as BaseBottomBarView
            bottomBarView.setBottomItemChecked(getBottomBarItem())
        }

    }
}