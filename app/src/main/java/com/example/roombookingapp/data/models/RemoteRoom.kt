package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteRoom(
    val id: Long,
    val description: String,
    val photos: List<RemotePhoto>,
    val type: String,
    val capacity: Long,
    val floor: Long,
    val reservationList: List<RemoteBooking>,
) : Parcelable