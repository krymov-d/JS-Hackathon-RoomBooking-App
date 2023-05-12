package com.example.roombookingapp.data.network

import com.example.roombookingapp.data.models.RegisterResponse
import com.example.roombookingapp.data.models.RemoteUserData
import com.example.roombookingapp.data.models.UserLogin
import com.example.roombookingapp.data.models.UserRegister
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("signup")
    suspend fun registerUser(@Body userRegister: UserRegister): RegisterResponse

    @POST("signin")
    suspend fun loginUser(@Body userLogin: UserLogin): RemoteUserData
}