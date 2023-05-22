package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.AddNewRoomUseCase
import com.example.roombookingapp.domain.use_cases.GetAllUsersUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import com.example.roombookingapp.domain.use_cases.SubmitBookingUseCase
import com.example.roombookingapp.domain.use_cases.UserLoginUseCase
import com.example.roombookingapp.domain.use_cases.UserRegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserLoginUseCase(usersRepository = get()) }
    factory { UserRegisterUseCase(usersRepository = get()) }
    factory { GetRoomsUseCase(roomsRepository = get()) }
    factory { GetRoomDetailsUseCase(roomsRepository = get()) }
    factory { SubmitBookingUseCase(bookingsRepository = get()) }
    factory { AddNewRoomUseCase(roomsRepository = get()) }
    factory { GetAllUsersUseCase(usersRepository = get()) }
}