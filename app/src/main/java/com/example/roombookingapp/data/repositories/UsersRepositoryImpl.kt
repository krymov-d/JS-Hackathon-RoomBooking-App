package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.models.User
import com.example.roombookingapp.data.models.RemoteSignInForm
import com.example.roombookingapp.data.models.RemoteSignUpForm
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.models.MakeAdminResponse
import com.example.roombookingapp.domain.repositories.UsersRepository

class UsersRepositoryImpl(private val mainApi: MainApi) : UsersRepository {

    override suspend fun userSignUp(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Int {
        val remoteSignUpForm =
            RemoteSignUpForm(name = name, surname = surname, email = email, password = password)
        return mainApi.userSignUp(remoteSignUpForm = remoteSignUpForm).statusCodeValue
    }

    override suspend fun userSignIn(email: String, password: String): SignInResponse {
        val remoteSignInForm = RemoteSignInForm(email = email, password = password)
        return mainApi.userSignIn(remoteSignInForm = remoteSignInForm)
    }

    override suspend fun getAllUsers(userId: String, userToken: String): List<User> {
        return mainApi.getAllUsers(userToken = "Bearer $userToken", userId = userId)
    }

    override suspend fun makeAdmin(userId: String, userToken: String): MakeAdminResponse {
        return mainApi.makeAdmin(userToken = "Bearer $userToken", userId = userId)
    }
}