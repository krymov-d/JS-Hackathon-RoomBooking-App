package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Booking(
    val id: Long,
    val userId: Long,
    val weekDay: String,
    val date: String,
    val time: String,
    val purpose: String,
) : Parcelable
