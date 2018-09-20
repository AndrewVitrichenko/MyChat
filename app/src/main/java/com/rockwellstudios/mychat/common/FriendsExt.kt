package com.rockwellstudios.mychat.common

import com.rockwellstudios.mychat.ui.main.friends.find.entity.User

fun isIncludedInMap(friendRequestsMap: HashMap<String,User?>,user: User?): Boolean = friendRequestsMap.containsKey(user?.email)