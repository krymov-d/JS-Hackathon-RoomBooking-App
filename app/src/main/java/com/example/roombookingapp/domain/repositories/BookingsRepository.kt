package com.example.roombookingapp.domain.repositories

interface BookingsRepository {

    suspend fun submitBooking(
        reason: String,
        day: Int,
        month: Int,
        year: Int,
        startHour: Int,
        startMinute: Int,
        endHour: Int,
        endMinute: Int
    )
}