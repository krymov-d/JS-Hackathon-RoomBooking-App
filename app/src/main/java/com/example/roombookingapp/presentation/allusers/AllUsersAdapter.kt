package com.example.roombookingapp.presentation.allusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.User
import com.example.roombookingapp.presentation.utils.ClickListener

class AllUsersAdapter(private val inflater: LayoutInflater) :
    ListAdapter<User, AllUsersViewHolder>(DiffCallback()) {

    var listener: ClickListener<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersViewHolder {
        val itemView = inflater.inflate(R.layout.item_user, parent, false)
        return AllUsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            listener?.onClick(user)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}