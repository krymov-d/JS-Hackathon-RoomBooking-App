package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookedTime(
    val id: Long,
    val roomId: Long,
    val userId: Long,
    val purpose: String,
    val period: TimeFrame,
) : Parcelable
