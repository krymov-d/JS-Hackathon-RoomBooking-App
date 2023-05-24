package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResponse(
    val headers: Map<String, String>,
    val body: SignUpResponseBody,
    val statusCode: String,
    val statusCodeValue: Int,
) : Parcelable