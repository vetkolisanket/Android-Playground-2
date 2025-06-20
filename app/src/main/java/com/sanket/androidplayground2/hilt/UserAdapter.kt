package com.sanket.androidplayground2.hilt

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanket.androidplayground2.commons.utils.loadImage
import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.databinding.ItemUserBinding

open class UserAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                imageViewAvatar.loadImage(user.avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(ItemUserBinding.bind(parent))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount() = users.size

    fun addData(users: List<User>) = this.users.addAll(users)

}
