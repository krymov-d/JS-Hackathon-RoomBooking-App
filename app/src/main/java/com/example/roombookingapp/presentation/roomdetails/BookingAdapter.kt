package com.example.roombookingapp.presentation.roomdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.roombookingapp.R

class BookingAdapter(private val inflater: LayoutInflater) :
    ListAdapter<Booking, BookingViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val itemView = inflater.inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = getItem(position)
        holder.bind(booking)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Booking>() {
    override fun areItemsTheSame(oldItem: Booking, newItem: Booking): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Booking, newItem: Booking): Boolean {
        return oldItem == newItem
    }
}