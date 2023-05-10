package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.RoomDetails

interface RoomsRepository {

    suspend fun getRooms(): List<Room>

    suspend fun getRoomPhotos(roomId: Long): List<String>

    suspend fun getRoomDetails(roomId: Long): RoomDetails

    suspend fun getRoomBookings(roomId: Long): List<Booking>
}