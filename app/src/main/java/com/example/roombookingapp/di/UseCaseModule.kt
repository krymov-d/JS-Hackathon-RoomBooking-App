package com.example.roombookingapp.di

import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRoomDetailsUseCase(get()) }
}