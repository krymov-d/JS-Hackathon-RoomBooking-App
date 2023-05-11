package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.constants.dateTimeFormatter
import com.example.roombookingapp.data.models.RemoteBooking
import com.example.roombookingapp.data.models.RemoteTimeFrame
import com.example.roombookingapp.domain.repositories.BookingsRepository
import java.time.OffsetDateTime
import java.time.ZoneOffset

class BookingsRepositoryImpl : BookingsRepository {
    override suspend fun submitBooking(
        reason: String,
        day: Int,
        month: Int,
        year: Int,
        startHour: Int,
        startMinute: Int,
        endHour: Int,
        endMinute: Int
    ) {
        val startDateTime =
            OffsetDateTime.of(year, month, day, startHour, startMinute, 0, 0, ZoneOffset.UTC)
        val endDateTime =
            OffsetDateTime.of(year, month, day, endHour, endMinute, 0, 0, ZoneOffset.UTC)

        val startDateTimeFormatted = startDateTime.format(dateTimeFormatter)
        val endDateTimeFormatted = endDateTime.format(dateTimeFormatter)

        val newBookingTimeFrame = RemoteTimeFrame(
            id = "Random string",
            startTime = startDateTimeFormatted,
            endTime = endDateTimeFormatted
        )
        val newBooking = RemoteBooking(
            reservationId = 0,
            userId = 0,
            description = reason,
            period = newBookingTimeFrame
        )
    }
}