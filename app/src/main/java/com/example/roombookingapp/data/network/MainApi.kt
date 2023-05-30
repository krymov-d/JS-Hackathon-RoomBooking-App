package com.example.roombookingapp.data.network

import com.example.roombookingapp.data.models.RemoteBooking
import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.data.models.RemoteSignInForm
import com.example.roombookingapp.data.models.RemoteSignUpForm
import com.example.roombookingapp.domain.models.MakeAdminResponse
import com.example.roombookingapp.domain.models.RemoveBookingResponse
import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.models.SignUpResponse
import com.example.roombookingapp.domain.models.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @POST("signup")
    suspend fun userSignUp(@Body remoteSignUpForm: RemoteSignUpForm): SignUpResponse

    @POST("signin")
    suspend fun userSignIn(@Body remoteSignInForm: RemoteSignInForm): SignInResponse

    @GET("clients")
    suspend fun getAllUsers(
        @Header("Authorization") userToken: String,
        @Query("userId") userId: String,
    ): List<User>

    @POST("make/admin")
    suspend fun makeAdmin(
        @Header("Authorization") userToken: String,
        @Query("userId") userId: String,
    ): MakeAdminResponse

    @GET("rooms")
    suspend fun getRooms(
        @Header("Authorization") userToken: String,
        @Query("userId") userId: String,
    ): List<RemoteRoom>

    @GET("room/{roomId}")
    suspend fun getRoomDetails(
        @Header("Authorization") userToken: String,
        @Path("roomId") roomId: String,
        @Query("userId") userId: String,
    ): RemoteRoom

    @POST("rooms/add")
    suspend fun addNewRoom(
        @Header("Authorization") userToken: String,
        @Query("userId") userId: String,
        @Body newRoom: RemoteRoom
    ): RemoteRoom

    @DELETE("rooms/delete")
    suspend fun deleteRoom(
        @Header("Authorization") userToken: String,
        @Query("roomId") roomId: String,
        @Query("userId") userId: String
    ): String

    @POST("reservation/add")
    suspend fun addNewBooking(
        @Header("Authorization") userToken: String,
        @Query("roomId") roomId: String,
        @Body booking: RemoteBooking
    ): RemoteBooking

    @DELETE("reservation/delete")
    suspend fun removeBooking(
        @Header("Authorization") userToken: String,
        @Query("reservationId") reservationId: String,
        @Query("roomId") roomId: String,
        @Query("userId") userId: String
    ): RemoveBookingResponse
}