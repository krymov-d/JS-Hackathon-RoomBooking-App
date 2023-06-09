package com.example.roombookingapp.presentation.roomdetails.bookings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.Booking

class BookingsAdapter(
    private val userId: Long,
    private val inflater: LayoutInflater
) :
    ListAdapter<Booking, BookingsViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        val booking = getItem(position)
        return if (booking.userId == userId) {
            R.layout.item_booking_highlighted
        } else {
            R.layout.item_booking
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val itemView = inflater.inflate(viewType, parent, false)
        return BookingsViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val booking = getItem(position)
        holder.bind(booking = booking)
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