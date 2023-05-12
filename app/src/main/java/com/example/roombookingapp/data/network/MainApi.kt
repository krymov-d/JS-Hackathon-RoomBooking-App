package com.example.roombookingapp.data.network

import com.example.roombookingapp.data.models.UserRegister
import com.example.roombookingapp.data.models.UserLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("signup")
    suspend fun registerUser(@Body userRegister: UserRegister)

    @POST("signin")
    suspend fun loginUser(@Body userLogin: UserLogin): String
}