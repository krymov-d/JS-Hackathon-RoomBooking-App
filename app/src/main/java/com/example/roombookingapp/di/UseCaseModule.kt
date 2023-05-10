package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.GetRoomBookingsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomPhotosUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRoomsUseCase(roomsRepository = get()) }
    factory { GetRoomPhotosUseCase(roomsRepository = get()) }
    factory { GetRoomDetailsUseCase(roomsRepository = get()) }
    factory { GetRoomBookingsUseCase(roomsRepository = get()) }
}