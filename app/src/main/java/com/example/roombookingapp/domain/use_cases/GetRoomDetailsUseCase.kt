package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.repositories.RoomsRepository

class GetRoomDetailsUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(roomId: Long): Room {
        return roomsRepository.getRoomDetails(roomId = roomId)
    }
}