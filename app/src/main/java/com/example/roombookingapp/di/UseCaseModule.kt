package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.GetRoomBookingsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomPhotosUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import com.example.roombookingapp.domain.use_cases.RegisterUserUseCase
import com.example.roombookingapp.domain.use_cases.SubmitBookingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRoomsUseCase(roomsRepository = get()) }
    factory { GetRoomPhotosUseCase(roomsRepository = get()) }
    factory { GetRoomDetailsUseCase(roomsRepository = get()) }
    factory { GetRoomBookingsUseCase(roomsRepository = get()) }
    factory { SubmitBookingUseCase(bookingsRepository = get()) }
    factory { RegisterUserUseCase(usersRepository = get()) }
}