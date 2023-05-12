package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomDetails(
    val id: Long,
    val category: String,
    val floor: Long,
    val capacity: Long,
    val description: String,
    val photoUrlList: List<String>,
    val bookingList: List<Booking>
) : Parcelable