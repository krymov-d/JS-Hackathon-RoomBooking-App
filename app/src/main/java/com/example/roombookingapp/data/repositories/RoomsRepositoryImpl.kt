package com.example.roombookingapp.data.repositories

import android.text.format.DateFormat
import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.repositories.RoomsRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class RoomsRepositoryImpl : RoomsRepository {
    override suspend fun getRoomPhotos(roomId: Long): List<String> {
        return listOf(
            "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
        )
    }

    override suspend fun getRoomDetails(roomId: Long): RoomDetails {
        return RoomDetails(
            id = 304,
            category = "Study Room",
            floor = 3,
            capacity = 30,
            description = "The best room for study!"
        )
    }

    override suspend fun getRoomBookings(roomId: Long): List<Booking> {
        val startTime = "2023-05-08T12:00:00.000Z"
        val endTime = "2023-05-08T12:30:00.000Z"

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz")
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm")
        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        val dateTimeStart = LocalDateTime.parse(startTime, formatter)
        val dateTimeEnd = LocalDateTime.parse(endTime, formatter)

        val formattedDate = dateTimeStart.format(dateFormatter)
        val formattedTimeStart = dateTimeStart.format(timeFormatter)
        val formattedTimeEnd = dateTimeEnd.format(timeFormatter)

        val weekDayFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedWeekDay = weekDayFormatter.parse(formattedDate)

        val weekDay = DateFormat.format("EEEE", formattedWeekDay)

        return listOf(
            Booking(
                id = 0,
                weekDay = "$weekDay",
                date = formattedDate,
                time = "$formattedTimeStart - $formattedTimeEnd",
                purpose = "Lecture"
            ),
            Booking(
                id = 1,
                weekDay = "$weekDay",
                date = formattedDate,
                time = "$formattedTimeStart - $formattedTimeEnd",
                purpose = "Meeting"
            ),
            Booking(
                id = 2,
                weekDay = "$weekDay",
                date = formattedDate,
                time = "$formattedTimeStart - $formattedTimeEnd",
                purpose = "Party"
            ),
            Booking(
                id = 3,
                weekDay = "$weekDay",
                date = formattedDate,
                time = "$formattedTimeStart - $formattedTimeEnd",
                purpose = "Free time"
            ),
            Booking(
                id = 4,
                weekDay = "$weekDay",
                date = formattedDate,
                time = "$formattedTimeStart - $formattedTimeEnd",
                purpose = "Movie"
            ),
        )
    }
}