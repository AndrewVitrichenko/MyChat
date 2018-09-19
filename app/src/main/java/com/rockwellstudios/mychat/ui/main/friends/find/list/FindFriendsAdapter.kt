package com.rockwellstudios.mychat.ui.main.friends.find.list

import android.support.constraint.ConstraintLayout
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import kotlinx.android.synthetic.main.item_user.view.*

class FindFriendsAdapter : RecyclerView.Adapter<FindFriendsAdapter.FindFriendsViewHolder>() {

    private var usersList: MutableList<User> = mutableListOf()

    private var userClickListener : OnUserClickListener? = null

    fun setOnUserClickListener(userClickListener: OnUserClickListener?){
        this.userClickListener = userClickListener
    }

    fun setUsers(usersList: MutableList<User>) {
        this.usersList.apply {
            clear()
            addAll(usersList)
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
        holder.bind(usersList[position])
    }


    class FindFriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                user.apply {
                    Glide.with(context).load(userPicture).into(imageViewUserPhoto)
                    textViewUserName.text = userName
                    textViewUserStatus.text = hasLoggedIn.toString()
                }
            }

        }

    }

    interface OnUserClickListener {

        fun onUserClick(user: User)

    }
}
