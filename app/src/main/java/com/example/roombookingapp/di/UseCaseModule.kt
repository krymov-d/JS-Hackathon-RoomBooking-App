package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.AddNewRoomUseCase
import com.example.roombookingapp.domain.use_cases.GetAllUsersUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import com.example.roombookingapp.domain.use_cases.AddNewBookingUseCase
import com.example.roombookingapp.domain.use_cases.DeleteRoomUseCase
import com.example.roombookingapp.domain.use_cases.MakeAdminUseCase
import com.example.roombookingapp.domain.use_cases.RemoveBookingUseCase
import com.example.roombookingapp.domain.use_cases.UserSignInUseCase
import com.example.roombookingapp.domain.use_cases.UserSignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserSignInUseCase(usersRepository = get()) }
    factory { UserSignUpUseCase(usersRepository = get()) }
    factory { GetRoomsUseCase(roomsRepository = get()) }
    factory { GetRoomDetailsUseCase(roomsRepository = get()) }
    factory { AddNewBookingUseCase(bookingsRepository = get()) }
    factory { AddNewRoomUseCase(roomsRepository = get()) }
    factory { GetAllUsersUseCase(usersRepository = get()) }
    factory { RemoveBookingUseCase(bookingsRepository = get()) }
    factory { MakeAdminUseCase(usersRepository = get()) }
    factory { DeleteRoomUseCase(roomsRepository = get()) }
}