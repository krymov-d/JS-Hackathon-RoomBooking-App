package com.example.roombookingapp.domain.models

data class Booking(
    val id: Long,
    val weekDay: String,
    val date: String,
    val time: String,
    val purpose: String,
)
