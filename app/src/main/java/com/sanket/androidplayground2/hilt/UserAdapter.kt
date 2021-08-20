package com.sanket.androidplayground2.hilt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.inflate
import com.sanket.androidplayground2.commons.utils.loadImage
import com.sanket.androidplayground2.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

open class UserAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                imageViewAvatar.loadImage(user.avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent.inflate(R.layout.item_user))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount() = users.size

    fun addData(users: List<User>) = this.users.addAll(users)

}
