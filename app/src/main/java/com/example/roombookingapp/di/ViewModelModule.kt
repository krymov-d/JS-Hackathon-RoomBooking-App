package com.example.roombookingapp.di

import com.example.roombookingapp.presentation.booking.RoomBookingViewModel
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsViewModel
import com.example.roombookingapp.presentation.rooms.RoomsViewModel
import com.example.roombookingapp.presentation.addroom.AddNewRoomViewModel
import com.example.roombookingapp.presentation.allusers.AllUsersViewModel
import com.example.roombookingapp.presentation.signin.SignInViewModel
import com.example.roombookingapp.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SignInViewModel(userSignInUseCase = get())
    }

    viewModel {
        SignUpViewModel(userSignUpUseCase = get())
    }

    viewModel { (userId: Long, userRole: String, userToken: String) ->
        RoomsViewModel(
            userId = userId,
            userRole = userRole,
            userToken = userToken,
            getRoomsUseCase = get(),
            deleteRoomUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String, roomId: String) ->
        RoomDetailsViewModel(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            getRoomDetailsUseCase = get(),
            removeBookingUseCase = get()
        )
    }

    viewModel { (userId: String, userToken: String, roomId: String) ->
        RoomBookingViewModel(
            userId = userId,
            userToken = userToken,
            roomId = roomId,
            addNewBookingUseCase = get()
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
            getAllUsersUseCase = get(),
            makeAdminUseCase = get()
        )
    }
}