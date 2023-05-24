package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteSignInForm(
    val email: String,
    val password: String
) : Parcelable