package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.data.mapper.toRoomDetails
import com.example.roombookingapp.data.mapper.toRoomsList
import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.repositories.RoomsRepository

class RoomsRepositoryImpl(private val mainApi: MainApi) : RoomsRepository {

    override suspend fun getRooms(
        userId: Long,
        userToken: String
    ): List<Room> {
        val remoteRooms =
            mainApi.getRooms(userToken = "Bearer $userToken", userId = userId.toString())
        return toRoomsList(remoteRooms)
    }

    override suspend fun getRoomDetails(
        userId: String,
        userToken: String,
        roomId: String
    ): RoomDetails {
        val remoteRoom = mainApi.getRoomDetails(
            userToken = "Bearer $userToken",
            roomId = roomId,
            userId = userId
        )
        return toRoomDetails(remoteRoom)
    }

    override suspend fun addNewRoom(
        userId: String,
        userToken: String,
        roomId: String,
        roomType: String,
        roomCapacity: String,
        roomFloor: String,
        roomDescription: String
    ): Long {
        val newRoom = RemoteRoom(
            id = roomId.toLong(),
            description = roomDescription,
            photos = emptyList(),
            type = roomType,
            capacity = roomCapacity.toLong(),
            floor = roomFloor.toLong(),
            reservationList = emptyList()
        )
        return mainApi.addNewRoom(
            userToken = "Bearer $userToken",
            userId = userId,
            newRoom = newRoom
        ).id
    }
}