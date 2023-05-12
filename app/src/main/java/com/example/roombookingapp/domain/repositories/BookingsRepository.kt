package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.data.models.RegisterResponse

interface BookingsRepository {

    suspend fun submitBooking(
        userId: String,
        userToken: String,
        roomId: String,
        reason: String,
        day: Int,
        month: Int,
        year: Int,
        startHour: Int,
        startMinute: Int,
        endHour: Int,
        endMinute: Int
    ): RegisterResponse
}