package com.example.roombookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteUser(
    val name: String,
    val surname: String
) : Parcelable
