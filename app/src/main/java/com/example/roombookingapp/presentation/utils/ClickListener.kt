package com.example.roombookingapp.presentation.utils

fun interface ClickListener<T : Any> {
    fun onClick(item: T)
}