package com.example.roombookingapp.data.mapper

import com.example.roombookingapp.constants.dateFormatter
import com.example.roombookingapp.constants.timeFormatter
import com.example.roombookingapp.constants.weekDayFormatter
import com.example.roombookingapp.data.models.RemoteBooking
import com.example.roombookingapp.data.models.RemotePhoto
import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.data.models.RemoteTimeFrame
import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.RoomDetails
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun toRoomDetails(remoteRoom: RemoteRoom): RoomDetails {
    return RoomDetails(
        id = remoteRoom.id,
        category = remoteRoom.type,
        floor = remoteRoom.floor,
        capacity = remoteRoom.capacity,
        description = remoteRoom.description,
        photoUrlList = toPhotosUrlList(remoteRoom.photos),
        bookingList = toBookingsList(remoteRoom.reservationList)
    )
}

fun toPhotosUrlList(remotePhotoList: List<RemotePhoto>): List<String> {
    return remotePhotoList.map { remotePhoto ->
        remotePhoto.name
    }
}

fun toBookingsList(remoteBookingList: List<RemoteBooking>): List<Booking> {
    return remoteBookingList.map { remoteBooking ->
        val bookingTime = toBookingTime(remoteBooking.period)
        Booking(
            id = remoteBooking.reservationId,
            weekDay = bookingTime[0],
            date = bookingTime[1],
            time = bookingTime[2],
            purpose = remoteBooking.description,
        )
    }
}

fun toBookingTime(remoteTimeFrame: RemoteTimeFrame): List<String> {

    val startDateTime =
        LocalDateTime.parse(remoteTimeFrame.startTime, DateTimeFormatter.ISO_DATE_TIME)
    val endDateTime = LocalDateTime.parse(remoteTimeFrame.endTime, DateTimeFormatter.ISO_DATE_TIME)

    val startHour = startDateTime.format(timeFormatter)
    val endHour = endDateTime.format(timeFormatter)

    val date = startDateTime.format(dateFormatter)
    val weekDay = startDateTime.format(weekDayFormatter)

    return listOf(weekDay, date, "$startHour - $endHour")
}