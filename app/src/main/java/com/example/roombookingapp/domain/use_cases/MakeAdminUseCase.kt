package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.MakeAdminResponse
import com.example.roombookingapp.domain.repositories.UsersRepository

class MakeAdminUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(userId: String, userToken: String): MakeAdminResponse {
        return usersRepository.makeAdmin(userId = userId, userToken = userToken)
    }
}