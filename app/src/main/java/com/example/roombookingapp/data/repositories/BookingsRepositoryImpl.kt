package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.data.mapper.toRemoteBooking
import com.example.roombookingapp.data.models.RegisterResponse
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.repositories.BookingsRepository

class BookingsRepositoryImpl(private val mainApi: MainApi) : BookingsRepository {
    override suspend fun submitBooking(
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
        return mainApi.submitBooking(
            userToken = userToken,
            roomId = roomId,
            booking = remoteBooking
        )
    }
}