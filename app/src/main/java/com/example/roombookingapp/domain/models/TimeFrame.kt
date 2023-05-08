package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class TimeFrame(
    val id: String,
    val startTime: String,
    val endTime: String
) : Parcelable
