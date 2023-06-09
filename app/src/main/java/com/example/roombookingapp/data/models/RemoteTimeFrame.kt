package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteTimeFrame(
    val clientId: Long,
    val roomNum: Long,
    val startTime: String,
    val endTime: String
) : Parcelable
