package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(
    val headers: Map<String, String>,
    val body: RegisterResponseBody,
    val statusCode: String,
    val statusCodeValue: Int,
) : Parcelable