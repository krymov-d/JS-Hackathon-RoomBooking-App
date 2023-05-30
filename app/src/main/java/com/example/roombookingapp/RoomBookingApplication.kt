package com.example.roombookingapp

import android.app.Application
import com.example.roombookingapp.di.networkModule
import com.example.roombookingapp.di.repositoryModule
import com.example.roombookingapp.di.useCaseModule
import com.example.roombookingapp.di.viewModelModule
import org.koin.core.context.startKoin

class RoomBookingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }
}