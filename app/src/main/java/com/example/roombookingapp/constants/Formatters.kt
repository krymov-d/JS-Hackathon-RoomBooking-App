package com.example.roombookingapp.constants

import java.time.format.DateTimeFormatter

val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("kk:mm")
val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
val weekDayFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE")
