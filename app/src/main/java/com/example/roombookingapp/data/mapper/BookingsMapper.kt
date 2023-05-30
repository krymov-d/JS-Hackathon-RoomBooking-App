package com.example.roombookingapp.data.mapper

import com.example.roombookingapp.constants.dateTimeFormatter
import com.example.roombookingapp.data.models.RemoteBooking
import com.example.roombookingapp.data.models.RemoteTimeFrame
import java.time.OffsetDateTime
import java.time.ZoneOffset

fun toRemoteBooking(
    userId: String,
    roomId: String,
    reason: String,
    day: Int,
    month: Int,
    year: Int,
    startHour: Int,
    startMinute: Int,
    endHour: Int,
    endMinute: Int
): RemoteBooking {

    val startDateTime =
        OffsetDateTime.of(year, month, day, startHour, startMinute, 0, 0, ZoneOffset.UTC)
    val endDateTime =
        OffsetDateTime.of(year, month, day, endHour, endMinute, 0, 0, ZoneOffset.UTC)

    val startDateTimeFormatted = startDateTime.format(dateTimeFormatter)
    val endDateTimeFormatted = endDateTime.format(dateTimeFormatter)

    val remoteTimeFrame = RemoteTimeFrame(
        clientId = userId.toLong(),
        roomNum = roomId.toLong(),
        startTime = startDateTimeFormatted,
        endTime = endDateTimeFormatted
    )

    return RemoteBooking(
        reservationId = 0,
        period = remoteTimeFrame,
        userId = userId.toLong(),
        description = reason,
        roomId = roomId.toLong()
    )
}