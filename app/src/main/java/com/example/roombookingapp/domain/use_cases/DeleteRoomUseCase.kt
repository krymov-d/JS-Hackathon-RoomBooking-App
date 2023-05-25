package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.RoomsRepository

class DeleteRoomUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(roomId: String, userId: String, userToken: String): String {
        return roomsRepository.deleteRoom(userId = userId, userToken = userToken, roomId = roomId)
    }
}