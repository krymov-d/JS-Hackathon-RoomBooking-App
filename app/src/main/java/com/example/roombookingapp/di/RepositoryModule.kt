package com.example.roombookingapp.di

import com.example.roombookingapp.data.repositories.BookingsRepositoryImpl
import com.example.roombookingapp.data.repositories.RoomsRepositoryImpl
import com.example.roombookingapp.data.repositories.UsersRepositoryImpl
import com.example.roombookingapp.domain.repositories.BookingsRepository
import com.example.roombookingapp.domain.repositories.RoomsRepository
import com.example.roombookingapp.domain.repositories.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UsersRepository> { UsersRepositoryImpl(mainApi = get()) }
    factory<RoomsRepository> { RoomsRepositoryImpl(mainApi = get()) }
    factory<BookingsRepository> { BookingsRepositoryImpl(mainApi = get()) }
}