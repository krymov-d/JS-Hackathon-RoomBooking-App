package com.example.roombookingapp.data.local

import com.example.roombookingapp.data.models.LoginResponse
import com.example.roombookingapp.data.models.RemoteUser
import com.example.roombookingapp.domain.repositories.UsersRepository

class LocalUsersRepositoryImpl : UsersRepository {

    override suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int {
        return 200
    }

    override suspend fun loginUser(email: String, password: String): LoginResponse {
        return LoginResponse(userId = 0, role = "ADMIN", jwtToken = "random")
    }

    override suspend fun getAllUsers(userId: String, userToken: String): List<RemoteUser> {
        return listOf(
            RemoteUser(name = "Admin", surname = "Admin"),
            RemoteUser(name = "User", surname = "User")
        )
    }
}