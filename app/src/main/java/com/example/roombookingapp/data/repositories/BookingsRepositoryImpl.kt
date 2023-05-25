package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.data.mapper.toRemoteBooking
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.repositories.BookingsRepository

class BookingsRepositoryImpl(private val mainApi: MainApi) : BookingsRepository {
    override suspend fun addNewBooking(
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
    ): Long {
        val remoteBooking = toRemoteBooking(
            userId = userId,
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
        return mainApi.addNewBooking(
            userToken = "Bearer $userToken",
            roomId = roomId,
            booking = remoteBooking
        ).reservationId
    }

    override suspend fun removeBooking(
        bookingId: String,
        roomId: String,
        userId: String,
        userToken: String
    ): String {
        return mainApi.removeBooking(
            userToken = "Bearer $userToken",
            reservationId = bookingId,
            roomId = roomId,
            userId = userId
        ).message
    }
}