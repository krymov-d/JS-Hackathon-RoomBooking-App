package com.example.roombookingapp.domain.repositories

interface BookingsRepository {

    suspend fun addNewBooking(
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
    ): Long

    suspend fun removeBooking(
        bookingId: String,
        roomId: String,
        userId: String,
        userToken: String,
    ): String
}