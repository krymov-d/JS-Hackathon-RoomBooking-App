package com.example.roombookingapp.domain.repositories

import com.example.roombookingapp.domain.models.MakeAdminResponse
import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.models.User

interface UsersRepository {

    suspend fun userSignUp(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int

    suspend fun userSignIn(email: String, password: String): SignInResponse

    suspend fun getAllUsers(
        userId: String,
        userToken: String,
    ): List<User>

    suspend fun makeAdmin(
        userId: String,
        userToken: String
    ) : MakeAdminResponse
}