package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.User
import com.example.roombookingapp.domain.repositories.UsersRepository

class GetAllUsersUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(userId: String, userToken: String): List<User> {
        return usersRepository.getAllUsers(userId = userId, userToken = userToken)
    }
}