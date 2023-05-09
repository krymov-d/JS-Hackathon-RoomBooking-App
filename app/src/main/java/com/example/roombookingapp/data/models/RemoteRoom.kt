package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteRoom(
    val id: Long,
    val type: String,
    val floor: Long,
    val capacity: Long,
    val description: String,
    val photos: List<String>,
    val reservationList: List<RemoteBooking>,
) : Parcelable