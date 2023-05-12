package com.example.roombookingapp.data.network

import com.example.roombookingapp.data.models.RegisterResponse
import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.data.models.RemoteUserData
import com.example.roombookingapp.data.models.UserLogin
import com.example.roombookingapp.data.models.UserRegister
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MainApi {
    @POST("signup")
    suspend fun registerUser(@Body userRegister: UserRegister): RegisterResponse

    @POST("signin")
    suspend fun loginUser(@Body userLogin: UserLogin): RemoteUserData

    @GET("rooms")
    suspend fun getRooms(
        @Header("Authorization") userToken: String,
        @Query("userId") userId: String,
    ): List<RemoteRoom>
}