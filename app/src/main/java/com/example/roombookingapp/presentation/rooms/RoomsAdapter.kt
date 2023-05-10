package com.example.roombookingapp.presentation.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.presentation.utils.ClickListener

class RoomsAdapter(private val inflater: LayoutInflater) :
    ListAdapter<Room, RoomsViewHolder>(DiffCallback()) {

    var listener: ClickListener<Room>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val itemView = inflater.inflate(R.layout.item_room, parent, false)
        return RoomsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val room = getItem(position)
        holder.bind(room)
        holder.itemView.setOnClickListener {
            listener?.onClick(room)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}