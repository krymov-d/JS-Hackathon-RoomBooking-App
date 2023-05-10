package com.example.roombookingapp.presentation.rooms

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.Room

class RoomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvRoomId: TextView = itemView.findViewById(R.id.item_room_tv_roomId)
    private val tvCategory: TextView = itemView.findViewById(R.id.item_room_tv_category)
    private val ivPhoto: ImageView = itemView.findViewById(R.id.item_room_iv_container)

    fun bind(room: Room) {
        tvRoomId.text = room.id.toString()
        tvCategory.text = room.category
        Glide.with(ivPhoto)
            .load(room.photoUrl)
            .error(R.drawable.iv_no_photography)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivPhoto)
    }
}