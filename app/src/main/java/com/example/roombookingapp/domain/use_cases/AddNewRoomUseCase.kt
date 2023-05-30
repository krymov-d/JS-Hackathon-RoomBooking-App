package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.RoomsRepository

class AddNewRoomUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(
        userId: String,
        userToken: String,
        roomId: String,
        roomType: String,
        roomCapacity: String,
        roomFloor: String,
        roomDescription: String
    ): Long {
        return roomsRepository.addNewRoom(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            roomType = roomType,
            roomCapacity = roomCapacity,
            roomFloor = roomFloor,
            roomDescription = roomDescription
        )
    }
}