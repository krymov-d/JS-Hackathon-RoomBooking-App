package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.BookingsRepository

class RemoveBookingUseCase(private val bookingsRepository: BookingsRepository) {

    suspend operator fun invoke(
        bookingId: String,
        roomId: String,
        userId: String,
        userToken: String,
    ): String {
        return bookingsRepository.removeBooking(
            bookingId = bookingId,
            roomId = roomId,
            userId = userId,
            userToken = userToken
        )
    }
}