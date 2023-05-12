package com.example.roombookingapp.data.repositories

import com.example.roombookingapp.constants.dateFormatter
import com.example.roombookingapp.constants.timeFormatter
import com.example.roombookingapp.constants.weekDayFormatter
import com.example.roombookingapp.data.mapper.toRoomsList
import com.example.roombookingapp.data.network.MainApi
import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.repositories.RoomsRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RoomsRepositoryImpl(private val mainApi: MainApi) : RoomsRepository {

    override suspend fun getRooms(
        userId: Long,
        userToken: String
    ): List<Room> {
        val remoteRooms =
            mainApi.getRooms(userToken = "Bearer $userToken", userId = userId.toString())
        return toRoomsList(remoteRooms)
    }

    override suspend fun getRoomPhotos(roomId: Long): List<String> {
        return listOf(
            "https://thumb.tildacdn.com/tild3462-3632-4366-b233-623039353532/-/cover/760x570/center/center/-/format/webp/photo1664884357_14.jpeg",
            "https://thumb.tildacdn.com/tild3035-3736-4032-b761-313938653330/-/cover/760x570/center/center/-/format/webp/photo1664884357_1.jpeg",
            "https://thumb.tildacdn.com/tild3031-6663-4838-b932-386138613932/-/cover/760x570/center/center/-/format/webp/photo1664884357_2.jpeg",
            "https://thumb.tildacdn.com/tild6234-3435-4663-b731-613132366364/-/cover/760x570/center/center/-/format/webp/photo1664884357_10.jpeg",
            "https://thumb.tildacdn.com/tild6534-3162-4339-b430-353232363036/-/cover/760x570/center/center/-/format/webp/photo1664884357_18.jpeg",
            "https://thumb.tildacdn.com/tild6435-3638-4463-a566-353063633637/-/cover/760x570/center/center/-/format/webp/photo1664884357.jpeg",
            "https://thumb.tildacdn.com/tild3366-3662-4761-b836-356639346166/-/cover/760x570/center/center/-/format/webp/photo1664884357_8.jpeg",
            "https://thumb.tildacdn.com/tild3666-3939-4339-a332-333163343733/-/cover/760x570/center/center/-/format/webp/photo1664884357_6.jpeg",
            "https://thumb.tildacdn.com/tild3864-6465-4665-a136-613930663335/-/cover/760x570/center/center/-/format/webp/photo1664884357_5.jpeg",
            "https://thumb.tildacdn.com/tild3961-3631-4462-a438-323837356666/-/cover/760x570/center/center/-/format/webp/photo1664884357_15.jpeg",
            "https://thumb.tildacdn.com/tild3332-6665-4865-b435-646164623334/-/cover/760x570/center/center/-/format/webp/photo1664884357_16.jpeg"
        )
    }

    override suspend fun getRoomDetails(roomId: Long): RoomDetails {
        return RoomDetails(
            id = 304,
            category = "Study Room",
            floor = 3,
            capacity = 30,
            description = "The best room for study!"
        )
    }

    override suspend fun getRoomBookings(roomId: Long): List<Booking> {
        val startTime = "2023-05-10T13:00:00.000Z"
        val endTime = "2023-05-10T01:00:00.000Z"

        val startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME)
        val endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME)

        val startHour = startDateTime.format(timeFormatter)
        val endHour = endDateTime.format(timeFormatter)

        val date = startDateTime.format(dateFormatter)
        val weekDay = startDateTime.format(weekDayFormatter)

        return listOf(
            Booking(
                id = 0,
                weekDay = weekDay,
                date = date,
                time = "$startHour - $endHour",
                purpose = "Lecture"
            ),
            Booking(
                id = 1,
                weekDay = weekDay,
                date = date,
                time = "$startHour - $endHour",
                purpose = "Meeting"
            ),
            Booking(
                id = 2,
                weekDay = weekDay,
                date = date,
                time = "$startHour - $endHour",
                purpose = "Party"
            ),
            Booking(
                id = 3,
                weekDay = weekDay,
                date = date,
                time = "$startHour - $endHour",
                purpose = "Free time"
            ),
            Booking(
                id = 4,
                weekDay = weekDay,
                date = date,
                time = "$startHour - $endHour",
                purpose = "Movie"
            ),
        )
    }
}