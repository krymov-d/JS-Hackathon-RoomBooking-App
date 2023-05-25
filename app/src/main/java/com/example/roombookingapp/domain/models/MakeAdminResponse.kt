package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MakeAdminResponse(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val role: String
) : Parcelable