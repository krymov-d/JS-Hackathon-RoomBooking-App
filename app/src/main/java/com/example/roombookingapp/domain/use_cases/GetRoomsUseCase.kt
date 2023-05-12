package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.repositories.RoomsRepository

class GetRoomsUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(
        userId: Long,
        userToken: String
    ): List<Room> {
        return roomsRepository.getRooms(
            userId = userId,
            userToken = userToken
        )
    }
}