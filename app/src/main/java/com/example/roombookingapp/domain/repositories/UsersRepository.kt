package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.data.models.RemoteUserData

interface UsersRepository {

    suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int

    suspend fun loginUser(email: String, password: String): RemoteUserData
}