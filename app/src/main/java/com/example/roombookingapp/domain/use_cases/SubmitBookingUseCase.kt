package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.data.models.RegisterResponse
import com.example.roombookingapp.domain.repositories.BookingsRepository

class SubmitBookingUseCase(private val bookingsRepository: BookingsRepository) {

    suspend operator fun invoke(
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
    ): RegisterResponse {
        return bookingsRepository.submitBooking(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            reason = reason,
            day = day,
            month = month,
            year = year,
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute
        )
    }
}