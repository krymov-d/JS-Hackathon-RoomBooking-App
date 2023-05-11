package com.example.roombookingapp.domain.use_cases

import com.example.roombookingapp.domain.repositories.UsersRepository

class RegisterUserUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        usersRepository.registerUser(
            name = name,
            surname = surname,
            email = email,
            password = password
        )
    }
}