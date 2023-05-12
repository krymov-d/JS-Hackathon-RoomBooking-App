package com.example.roombookingapp.di

import com.example.roombookingapp.presentation.booking.RoomBookingViewModel
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsViewModel
import com.example.roombookingapp.presentation.rooms.RoomsViewModel
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

    viewModel { (roomId: Long) ->
        RoomDetailsViewModel(
            roomId = roomId,
            getRoomPhotosUseCase = get(),
            getRoomDetailsUseCase = get(),
            getRoomBookingsUseCase = get(),
        )
    }

    viewModel {
        RoomBookingViewModel(submitBookingUseCase = get())
    }

}