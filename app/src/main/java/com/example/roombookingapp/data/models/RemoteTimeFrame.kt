package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.OkHttpClient

@Parcelize
data class RemoteTimeFrame(
    val clientId: Long,
    val roomId: Long,
    val startTime: String,
    val endTime: String
) : Parcelable
