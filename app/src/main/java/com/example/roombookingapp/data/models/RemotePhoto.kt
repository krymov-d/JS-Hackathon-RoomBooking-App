package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemotePhoto(
    val photoId: Long,
    val roomId: Long,
    val name: String,
) : Parcelable
