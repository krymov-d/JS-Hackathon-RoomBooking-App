package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.GetRoomBookingsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomPhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRoomPhotosUseCase(get()) }
    factory { GetRoomDetailsUseCase(get()) }
    factory { GetRoomBookingsUseCase(get()) }
}