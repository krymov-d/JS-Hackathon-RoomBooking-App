package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResponseBody(
    val message: String
) : Parcelable