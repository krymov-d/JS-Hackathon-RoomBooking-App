package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.data.models.RemoteUser
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.repositories.UsersRepository

class UsersRepositoryImpl(private val mainApi: MainApi) : UsersRepository {

    override suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        val newUser = RemoteUser(name = name, surname = surname, email = email, password = password)
        mainApi.registerUser(newUser = newUser)
    }
}