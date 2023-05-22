package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.data.models.LoginResponse
import com.example.roombookingapp.data.models.RemoteUser
import com.example.roombookingapp.data.models.UserLogin
import com.example.roombookingapp.data.models.UserRegister
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.repositories.UsersRepository

class UsersRepositoryImpl(private val mainApi: MainApi) : UsersRepository {

    override suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int {
        val userRegister =
            UserRegister(name = name, surname = surname, email = email, password = password)
        return mainApi.registerUser(userRegister = userRegister).statusCodeValue
    }

    override suspend fun loginUser(email: String, password: String): LoginResponse {
        val userLogin = UserLogin(email = email, password = password)
        return mainApi.loginUser(userLogin = userLogin)
    }

    override suspend fun getAllUsers(userId: String, userToken: String): List<RemoteUser> {
        return mainApi.getAllUsers(userToken = "Bearer $userToken", userId = userId)
    }
}