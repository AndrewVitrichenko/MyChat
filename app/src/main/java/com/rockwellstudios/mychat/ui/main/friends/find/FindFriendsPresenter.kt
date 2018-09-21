package com.rockwellstudios.mychat.ui.main.friends.find

import com.rockwellstudios.mychat.common.ADD_FRIEND_REQUEST
import com.rockwellstudios.mychat.common.REMOVE_FRIEND_REQUEST
import com.rockwellstudios.mychat.common.isIncludedInMap
import com.rockwellstudios.mychat.ui.main.friends.find.datasource.FriendsDataSource
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class FindFriendsPresenter @Inject constructor(val view: FindFriendsContract.View,
                                               val compositeDisposable: CompositeDisposable,
                                               val dataSource: FriendsDataSource) : FindFriendsContract.Presenter {

    private var friendRequestsSentMap: HashMap<String, User?> = hashMapOf()
    private var friendRequestsReceivedMap: HashMap<String, User?> = hashMapOf()
    private var usersList: MutableList<User?> = arrayListOf()

    override fun attach() {
        dataSource.listenUserEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe {
                    setUsersList(it, false)
                }

        view.searchInputStream()
                .map { searchString ->
                    if (!searchString.isEmpty()) {
                        return@map usersList.asSequence().filter {
                            it?.userName?.toLowerCase() == searchString
                        }
                                .toMutableList()
                    } else{
                        return@map usersList
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe {
                    setUsersList(it, true)
                }

        dataSource.listenFriendsRequestsSentEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe {
                    setFriendsRequestSentMap(it)
                }

        dataSource.listenFriendsRequestsReceivedEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe {
                    setFriendsRequestReceivedMap(it)
                }

    }

    private fun setUsersList(usersList: MutableList<User?>, isSearching: Boolean) {
        if (!isSearching) {
            this.usersList.apply {
                clear()
                addAll(usersList)
                view.setUsersList(this)
            }
        } else {
            view.setUsersList(usersList)
        }
    }

    private fun setFriendsRequestSentMap(friendRequestsSentMap: HashMap<String, User?>) {
        this.friendRequestsSentMap.apply {
            clear()
            putAll(friendRequestsSentMap)
            view.setFriendsRequestSentMap(this)
        }
    }

    private fun setFriendsRequestReceivedMap(friendRequestsReceivedMap: HashMap<String, User?>) {
        this.friendRequestsReceivedMap.apply {
            clear()
            putAll(friendRequestsReceivedMap)
            view.setFriendsRequestReceivedMap(this)
        }
    }

    override fun onUserClick(user: User?) {
        if (isIncludedInMap(friendRequestsSentMap, user)) {
            dataSource.cancelFriendRequest(user)
            dataSource.addOrRemoveFriendRequest(user?.email!!, REMOVE_FRIEND_REQUEST)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { compositeDisposable.add(it) }
                    .subscribe()
        } else {
            dataSource.sendFriendRequest(user)
            dataSource.addOrRemoveFriendRequest(user?.email!!, ADD_FRIEND_REQUEST)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { compositeDisposable.add(it) }
                    .subscribe()

        }
    }


    override fun detach() {
        dataSource.stopEventsListening()
        compositeDisposable.clear()
    }

}