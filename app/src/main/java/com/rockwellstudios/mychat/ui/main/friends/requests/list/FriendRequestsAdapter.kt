package com.rockwellstudios.mychat.ui.main.friends.requests.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rockwellstudios.mychat.R
import com.rockwellstudios.mychat.ui.main.friends.find.entity.User
import kotlinx.android.synthetic.main.item_friend_request.view.*

class FriendRequestsAdapter : RecyclerView.Adapter<FriendRequestsAdapter.FriendRequestViewHolder>() {

    private var mUsers:MutableList<User?> = arrayListOf()
    private var onOptionClickListener: OnOptionClickListener? = null

    public fun setOnOptionClickListenerr(onOptionClickListener: OnOptionClickListener){
        this.onOptionClickListener = onOptionClickListener
    }

    public fun setUsers(mUsers:MutableList<User?>){
        this.mUsers.apply {
            clear()
            addAll(mUsers)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.item_friend_request,parent,false)
        val viewHolder = FriendRequestViewHolder(itemView)
        viewHolder.apply {
            itemView.apply {
                imageViewAcceptFriendRequest.setOnClickListener {
                    val user = mUsers[adapterPosition]
                    onOptionClickListener?.onRequestAccepted(user)
                }
                imageViewRejectFriendRequest.setOnClickListener {
                    val user = mUsers[adapterPosition]
                    onOptionClickListener?.onRequestRejected(user)
                }
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = mUsers.size

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
       holder.bind(mUsers[position])
    }


    inner class FriendRequestViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user:User?){
            itemView?.apply {
                user?.apply {
                    Glide.with(context).load(userPicture).apply(RequestOptions().dontAnimate()).into(imageViewUserPhoto)
                    textViewUserName.text = userName
                }
            }
        }

    }

    interface OnOptionClickListener{

        fun onRequestAccepted(user: User?)

        fun onRequestRejected(user: User?)

    }
}