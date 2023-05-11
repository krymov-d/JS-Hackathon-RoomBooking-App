package com.example.roombookingapp.data.network

import com.example.roombookingapp.data.models.RemoteUser
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("signup")
    suspend fun registerUser(@Body newUser: RemoteUser)
}