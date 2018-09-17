package com.rockwellstudios.mychat.ui.main.friends

import javax.inject.Inject

class FriendsPresenter @Inject constructor(val view : FriendsContract.View): FriendsContract.Presenter {

    override fun attach() {
        view.initPager()
    }

    override fun detach() {

    }

}