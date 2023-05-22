package com.example.roombookingapp.di

import com.example.roombookingapp.presentation.booking.RoomBookingViewModel
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsViewModel
import com.example.roombookingapp.presentation.rooms.RoomsViewModel
import com.example.roombookingapp.presentation.rooms.addroom.AddNewRoomViewModel
import com.example.roombookingapp.presentation.rooms.allusers.AllUsersViewModel
import com.example.roombookingapp.presentation.signin.SignInViewModel
import com.example.roombookingapp.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SignInViewModel(userLoginUseCase = get())
    }

    viewModel {
        SignUpViewModel(userRegisterUseCase = get())
    }

    viewModel { (userId: Long, userRole: String, userToken: String) ->
        RoomsViewModel(
            userId = userId,
            userRole = userRole,
            userToken = userToken,
            getRoomsUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String, roomId: String) ->
        RoomDetailsViewModel(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            getRoomDetailsUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String, roomId: String) ->
        RoomBookingViewModel(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            submitBookingUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String) ->
        AddNewRoomViewModel(
            userId = userId,
            userToken = userToken,
            addNewRoomUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String) ->
        AllUsersViewModel(
            userId = userId,
            userToken = userToken,
            getAllUsersUseCase = get()
        )
    }
}