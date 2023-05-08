package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val id: Long,
    val category: String,
    val floor: Long,
    val capacity: Long,
    val description: String,
    val photos: List<String>,
    val bookedTimeList: List<BookedTime>,
) : Parcelable