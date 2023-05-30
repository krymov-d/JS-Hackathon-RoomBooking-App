package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInResponse(
    val userId: Long,
    val role: String,
    val jwtToken: String,
) : Parcelable
