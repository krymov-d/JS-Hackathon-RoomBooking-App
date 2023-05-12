package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.RoomDetails

interface RoomsRepository {

    suspend fun getRooms(
        userId: Long,
        userToken: String
    ): List<Room>

    suspend fun getRoomDetails(
        userId: String,
        userToken: String,
        roomId: String
    ): RoomDetails
}