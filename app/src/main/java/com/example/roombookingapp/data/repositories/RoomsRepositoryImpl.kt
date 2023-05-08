package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.domain.models.BookedTime
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.TimeFrame
import com.example.roombookingapp.domain.repositories.RoomsRepository

class RoomsRepositoryImpl : RoomsRepository {
    override suspend fun getRoomDetails(roomId: Long): Room {
        val startTime = "2023-05-08T12:00:00.000Z"
        val endTime = "2023-05-08T12:30:00.000Z"
        val period = TimeFrame(id = "TimeFrameId", startTime = startTime, endTime = endTime)
        val bookedTime =
            BookedTime(id = 1, roomId = 304, userId = 1, purpose = "Lecture", period = period)
        return Room(
            id = 304,
            category = "Study Room",
            floor = 3,
            capacity = 30,
            description = "The best room for study!",
            photos = listOf(
                "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
                "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
                "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
                "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
            ),
            bookedTimeList = listOf(bookedTime)
        )
    }
}