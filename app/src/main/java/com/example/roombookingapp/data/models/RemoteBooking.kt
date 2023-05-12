package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteBooking(
    val reservationId: Long,
    val period: RemoteTimeFrame,
    val userId: Long,
    val description: String,
) : Parcelable
