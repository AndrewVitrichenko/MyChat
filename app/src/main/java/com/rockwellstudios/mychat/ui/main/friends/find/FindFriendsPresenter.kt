package com.rockwellstudios.mychat.ui.main.friends.find

import com.rockwellstudios.mychat.ui.main.friends.find.datasource.FriendsDataSource
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FindFriendsPresenter @Inject constructor(val view : FindFriendsContract.View,
                                               val compositeDisposable: CompositeDisposable,
                                               val dataSource: FriendsDataSource): FindFriendsContract.Presenter {

    override fun attach() {
        var user1 = User("Name 1","name1@mail.com","https://picsum.photos/200",true)
        var user2 = User("Name 2","name2@mail.com","https://picsum.photos/200",false)

        val userList : MutableList<User> = mutableListOf()

        userList.add(user1)
        userList.add(user2)

        view.setUsersList(userList)

        dataSource.listenEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe {

                }

    }

    override fun onUserClick(user: User) {
        dataSource.sendFriendRequest(user)
    }


    override fun detach() {
        dataSource.stopEventListening()
    }

}