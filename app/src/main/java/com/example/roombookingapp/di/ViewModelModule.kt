package com.example.roombookingapp.di

import com.example.roombookingapp.presentation.roomdetails.RoomDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (roomId: Long) ->
        RoomDetailsViewModel(
            roomId = roomId,
            getRoomDetailsUseCase = get()
        )
    }
}