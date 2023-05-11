package com.example.roombookingapp.data.repositories

import android.util.Log
import com.example.roombookingapp.data.models.RemoteUser
import com.example.roombookingapp.domain.repositories.UsersRepository

class UsersRepositoryImpl : UsersRepository {

    override suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        val newUser = RemoteUser(name = name, surname = surname, email = email, password = password)
        Log.d("NewUser", "$newUser")
    }
}