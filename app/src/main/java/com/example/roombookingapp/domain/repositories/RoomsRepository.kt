package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.domain.models.Room

interface RoomsRepository {

    suspend fun getRoomDetails(roomId: Long): Room
}