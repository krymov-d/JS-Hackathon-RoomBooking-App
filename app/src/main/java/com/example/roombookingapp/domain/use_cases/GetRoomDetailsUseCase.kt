package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.repositories.RoomsRepository

class GetRoomDetailsUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(
        userId: String,
        userToken: String,
        roomId: String
    ): RoomDetails {
        return roomsRepository.getRoomDetails(
            userId = userId,
            userToken = userToken,
            roomId = roomId
        )
    }
}