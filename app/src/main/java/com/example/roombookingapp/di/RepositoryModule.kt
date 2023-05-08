package com.example.roombookingapp.di

import com.example.roombookingapp.data.repositories.RoomsRepositoryImpl
import com.example.roombookingapp.domain.repositories.RoomsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<RoomsRepository> { RoomsRepositoryImpl() }
}