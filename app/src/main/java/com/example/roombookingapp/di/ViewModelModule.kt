package com.example.roombookingapp.di

import com.example.roombookingapp.presentation.booking.RoomBookingViewModel
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsViewModel
import com.example.roombookingapp.presentation.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (roomId: Long) ->
        RoomDetailsViewModel(
            roomId = roomId,
            getRoomPhotosUseCase = get(),
            getRoomDetailsUseCase = get(),
            getRoomBookingsUseCase = get(),
        )
    }
    viewModel {
        RoomsViewModel(getRoomsUseCase = get())
    }
    viewModel {
        RoomBookingViewModel(submitBookingUseCase = get())
    }
}