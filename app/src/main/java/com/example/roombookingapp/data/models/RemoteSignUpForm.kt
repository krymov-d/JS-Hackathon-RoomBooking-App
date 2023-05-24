package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteSignUpForm(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
) : Parcelable