package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val id: Long,
    val category: String,
    val photoUrlList: List<String>,
) : Parcelable
