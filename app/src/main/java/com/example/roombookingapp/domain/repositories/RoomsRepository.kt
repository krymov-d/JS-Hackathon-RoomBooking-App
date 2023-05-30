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

    suspend fun addNewRoom(
        userId: String,
        userToken: String,
        roomId: String,
        roomType: String,
        roomCapacity: String,
        roomFloor: String,
        roomDescription: String
    ): Long

    suspend fun deleteRoom(
        userId: String,
        userToken: String,
        roomId: String,
    ): String
}