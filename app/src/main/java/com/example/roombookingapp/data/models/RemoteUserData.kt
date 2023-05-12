package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteUserData(
    val userId: Long,
    val role: String,
    val jwtToken: String,
) : Parcelable
