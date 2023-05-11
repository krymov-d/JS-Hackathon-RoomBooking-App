package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.BookingsRepository

class SubmitBookingUseCase(private val bookingsRepository: BookingsRepository) {

    suspend operator fun invoke(
        reason: String,
        day: Int,
        month: Int,
        year: Int,
        startHour: Int,
        startMinute: Int,
        endHour: Int,
        endMinute: Int
    ) {
        bookingsRepository.submitBooking(
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