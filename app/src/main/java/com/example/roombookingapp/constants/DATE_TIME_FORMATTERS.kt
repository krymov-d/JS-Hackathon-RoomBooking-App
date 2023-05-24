package com.example.roombookingapp.constants

import java.time.format.DateTimeFormatter

val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("kk:mm")
val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
val weekDayFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE")
