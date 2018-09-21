package com.rockwellstudios.mychat.ui.main.friends.find.list

import android.support.constraint.ConstraintLayout
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.common.isIncludedInMap
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*
import kotlin.collections.HashMap

class FindFriendsAdapter : RecyclerView.Adapter<FindFriendsAdapter.FindFriendsViewHolder>() {

    private var usersList: MutableList<User?> = mutableListOf()

    private var friendRequestsSentMap: HashMap<String, User?> = hashMapOf()

    private var friendRequestsReceivedMap: HashMap<String, User?> = hashMapOf()

    private var userClickListener: OnUserClickListener? = null

    fun setOnUserClickListener(userClickListener: OnUserClickListener?) {
        this.userClickListener = userClickListener
    }

    fun setUsers(usersList: MutableList<User?>) {
        this.usersList.apply {
            clear()
            addAll(usersList)
            notifyDataSetChanged()
        }
    }

    fun setFriendRequestSentMap(friendRequestsSentMap: HashMap<String, User?>) {
        this.friendRequestsSentMap.apply {
            clear()
            putAll(friendRequestsSentMap)
            notifyDataSetChanged()
        }
    }

    fun setFriendRequestReceivedMap(friendRequestsReceivedMap: HashMap<String, User?>) {
        this.friendRequestsReceivedMap.apply {
            clear()
            putAll(friendRequestsReceivedMap)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindFriendsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        val findFriendsViewHolder = FindFriendsViewHolder(itemView)
        findFriendsViewHolder.apply {
            itemView.setOnClickListener {
                val user = usersList[adapterPosition]
                userClickListener?.onUserClick(user)
            }
        }
        return findFriendsViewHolder
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: FindFriendsViewHolder, position: Int) {
        holder.bind(usersList[position], friendRequestsSentMap,friendRequestsReceivedMap)
    }


    class FindFriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User?, friendRequestsSentMap: HashMap<String, User?>,
                 friendRequestsReceivedMap: HashMap<String, User?>) {
            itemView?.apply {
                user?.apply {
                    Glide.with(context).load(userPicture).into(imageViewUserPhoto)
                    textViewUserName.text = userName
                    when {
                        isIncludedInMap(friendRequestsSentMap, user) -> {
                            imageViewFriendRequest.visibility = View.VISIBLE
                            textViewUserStatus.visibility = View.VISIBLE
                            textViewUserStatus.text = context.getString(R.string.title_friend_request_send)
                            imageViewFriendRequest.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.svg_close))
                        }
                        isIncludedInMap(friendRequestsReceivedMap,user) -> {
                            imageViewFriendRequest.visibility = View.GONE
                            textViewUserStatus.visibility = View.VISIBLE
                            textViewUserStatus.text = context.getString(R.string.title_user_requested_you)
                        }
                        else -> {
                            imageViewFriendRequest.visibility = View.VISIBLE
                            textViewUserStatus.visibility = View.GONE
                            textViewUserStatus.text = ""
                            imageViewFriendRequest.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.svg_person_add))
                        }
                    }
                }
            }
        }
    }


    interface OnUserClickListener {

        fun onUserClick(user: User?)

    }
}
