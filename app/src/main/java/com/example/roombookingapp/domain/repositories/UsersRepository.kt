package com.example.roombookingapp.domain.repositories

interface UsersRepository {

    suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    )

    suspend fun loginUser(email: String, password: String): String
}