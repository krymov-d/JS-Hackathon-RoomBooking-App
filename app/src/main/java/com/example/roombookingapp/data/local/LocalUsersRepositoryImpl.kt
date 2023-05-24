package com.example.roombookingapp.data.local

import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.models.User
import com.example.roombookingapp.domain.repositories.UsersRepository

class LocalUsersRepositoryImpl : UsersRepository {

    override suspend fun userSignUp(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int {
        return 200
    }

    override suspend fun userSignIn(email: String, password: String): SignInResponse {
        return SignInResponse(userId = 0, role = "ADMIN", jwtToken = "random")
    }

    override suspend fun getAllUsers(userId: String, userToken: String): List<User> {
        return listOf(
            User(name = "Admin", surname = "Admin"),
            User(name = "User", surname = "User")
        )
    }
}