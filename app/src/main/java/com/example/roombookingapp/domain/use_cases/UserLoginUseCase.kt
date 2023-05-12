package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.data.models.LoginResponse
import com.example.roombookingapp.domain.repositories.UsersRepository

class UserLoginUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(email: String, password: String): LoginResponse {
        return usersRepository.loginUser(email = email, password = password)
    }
}