package com.example.roombookingapp.presentation.roomdetails

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R

class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvDate: TextView = itemView.findViewById(R.id.room_details_tv_date)
    private val tvTime: TextView = itemView.findViewById(R.id.room_details_tv_time)
    private val tvPurpose: TextView = itemView.findViewById(R.id.room_details_tv_purpose)

    fun bind(booking: Booking) {
        tvDate.text = booking.date
        tvTime.text = booking.time
        tvPurpose.text = booking.purpose
    }
}