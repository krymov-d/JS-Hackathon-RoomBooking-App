package com.example.roombookingapp.presentation.roomdetails.bookings

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R
import com.example.roombookingapp.domain.models.Booking
import com.google.android.material.card.MaterialCardView

class BookingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cvBooking: MaterialCardView = itemView.findViewById(R.id.room_details_cv_booking)
    private val tvWeekDay: TextView = itemView.findViewById(R.id.room_details_tv_weekday)
    private val tvDate: TextView = itemView.findViewById(R.id.room_details_tv_date)
    private val tvTime: TextView = itemView.findViewById(R.id.room_details_tv_time)
    private val tvPurpose: TextView = itemView.findViewById(R.id.room_details_tv_purpose)

    fun bind(booking: Booking, userId: Long) {
        if (userId == booking.userId) {
            cvBooking.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.ui_08
                )
            )
        }
        tvWeekDay.text = booking.weekDay
        tvDate.text = booking.date
        tvTime.text = booking.time
        tvPurpose.text = booking.purpose
    }
}