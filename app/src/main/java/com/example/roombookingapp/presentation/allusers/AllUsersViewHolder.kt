package com.example.roombookingapp.presentation.allusers

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.User

class AllUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName: TextView = itemView.findViewById(R.id.item_user_tv_name)
    private val tvSurname: TextView = itemView.findViewById(R.id.item_user_tv_surname)
    private val ivLabel: AppCompatImageView = itemView.findViewById(R.id.item_user_iv_label)

    fun bind(user: User) {
        tvName.text = user.name
        tvSurname.text = user.surname
        if (user.role == "ADMIN") {
            ivLabel.isVisible = true
        }
    }
}