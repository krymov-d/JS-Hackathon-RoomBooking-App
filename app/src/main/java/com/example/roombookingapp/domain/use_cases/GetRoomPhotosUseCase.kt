package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.RoomsRepository

class GetRoomPhotosUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(roomId: Long): List<String> {
        return roomsRepository.getRoomPhotos(roomId = roomId)
    }
}