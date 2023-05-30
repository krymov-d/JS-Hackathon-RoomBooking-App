package com.example.roombookingapp.data.local

import com.example.roombookingapp.domain.models.MakeAdminResponse
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
            User(name = "Admin", surname = "Admin", id = 1, role = "ADMIN"),
            User(name = "User", surname = "User", id = 2, role = "USER")
        )
    }

    override suspend fun makeAdmin(userId: String, userToken: String): MakeAdminResponse {
        return MakeAdminResponse(
            id = 2,
            name = "User",
            surname = "User",
            email = "User",
            password = "User",
            role = "ADMIN"
        )
    }
}