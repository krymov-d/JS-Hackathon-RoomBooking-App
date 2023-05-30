package com.example.roombookingapp.data.local

import com.example.roombookingapp.data.models.RemoteBooking
import com.example.roombookingapp.data.models.RemoteTimeFrame

val localBookingList = listOf(
    RemoteBooking(
        reservationId = 0,
        period = RemoteTimeFrame(
            clientId = 0,
            roomNum = 0,
            startTime = "0000-00-00T00:00:00.000Z",
            endTime = "0000-00-00T00:00:00.000Z"
        ),
        userId = 0,
        description = "",
        roomId = 0
    )
)