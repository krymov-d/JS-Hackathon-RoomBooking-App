package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteTimeFrame(
    val id: String,
    val startTime: String,
    val endTime: String
) : Parcelable
