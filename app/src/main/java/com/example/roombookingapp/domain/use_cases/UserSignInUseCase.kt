package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.repositories.UsersRepository

class UserSignInUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(email: String, password: String): SignInResponse {
        return usersRepository.userSignIn(email = email, password = password)
    }
}