package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.repositories.RoomsRepository

class GetRoomBookingsUseCase(private val roomsRepository: RoomsRepository) {

    suspend operator fun invoke(roomId: Long): List<Booking> {
        return roomsRepository.getRoomBookings(roomId = roomId)
    }
}