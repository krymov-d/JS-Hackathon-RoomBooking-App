package com.example.roombookingapp.data.local

import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.repositories.RoomsRepository

class LocalRoomsRepositoryImpl : RoomsRepository {

    override suspend fun getRooms(userId: Long, userToken: String): List<Room> {
        return listOf(
            Room(
                id = 301,
                category = "Kitchen",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild3031-6663-4838-b932-386138613932/-/cover/760x570/center/center/-/format/webp/photo1664884357_2.jpeg"
                )
            ),
            Room(
                id = 302,
                category = "Study Room I",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild3366-3662-4761-b836-356639346166/-/cover/760x570/center/center/-/format/webp/photo1664884357_8.jpeg"
                )
            ),
            Room(
                id = 303,
                category = "Conference Room",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild6234-3435-4663-b731-613132366364/-/cover/760x570/center/center/-/format/webp/photo1664884357_10.jpeg"
                )
            ),
            Room(
                id = 304,
                category = "Study Room II",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild3462-3632-4366-b233-623039353532/-/cover/760x570/center/center/-/format/webp/photo1664884357_14.jpeg"
                )
            ),
            Room(
                id = 305,
                category = "Administration",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild3366-3662-4761-b836-356639346166/-/cover/760x570/center/center/-/format/webp/photo1664884357_8.jpeg"
                )
            ),
            Room(id = 306, category = "Service Room", photoUrlList = emptyList()),
            Room(id = 403, category = "Office Room", photoUrlList = emptyList()),
            Room(
                id = 404,
                category = "Study Room III",
                photoUrlList = listOf(
                    "https://thumb.tildacdn.com/tild6435-3638-4463-a566-353063633637/-/cover/760x570/center/center/-/format/webp/photo1664884357.jpeg"
                )
            )
        )
    }

    override suspend fun getRoomDetails(
        userId: String,
        userToken: String,
        roomId: String
    ): RoomDetails {
        return RoomDetails(
            id = roomId.toLong(),
            category = "General category",
            floor = 3,
            capacity = 300,
            description = "General description",
            photoUrlList = listOf(
                "https://thumb.tildacdn.com/tild3031-6663-4838-b932-386138613932/-/cover/760x570/center/center/-/format/webp/photo1664884357_2.jpeg",
                "https://thumb.tildacdn.com/tild3366-3662-4761-b836-356639346166/-/cover/760x570/center/center/-/format/webp/photo1664884357_8.jpeg",
                "https://thumb.tildacdn.com/tild6234-3435-4663-b731-613132366364/-/cover/760x570/center/center/-/format/webp/photo1664884357_10.jpeg",
                "https://thumb.tildacdn.com/tild3462-3632-4366-b233-623039353532/-/cover/760x570/center/center/-/format/webp/photo1664884357_14.jpeg",
                "https://thumb.tildacdn.com/tild3366-3662-4761-b836-356639346166/-/cover/760x570/center/center/-/format/webp/photo1664884357_8.jpeg",
                "https://thumb.tildacdn.com/tild6435-3638-4463-a566-353063633637/-/cover/760x570/center/center/-/format/webp/photo1664884357.jpeg",
            ),
            bookingList = listOf(
                Booking(
                    id = 0,
                    userId = 1,
                    weekDay = "Thursday",
                    date = "01.06.2023",
                    time = "14:00 - 14.30",
                    purpose = "Meeting"
                ),
                Booking(
                    id = 1,
                    userId = 1,
                    weekDay = "Friday",
                    date = "02.06.2023",
                    time = "12:00 - 15.30",
                    purpose = "Lecture"
                ),
                Booking(
                    id = 2,
                    userId = 1,
                    weekDay = "Saturday",
                    date = "03.06.2023",
                    time = "09:00 - 18.00",
                    purpose = "Conference"
                ),
                Booking(
                    id = 3,
                    userId = 1,
                    weekDay = "Sunday",
                    date = "04.06.2023",
                    time = "14:00 - 14.30",
                    purpose = "Meeting"
                ),
                Booking(
                    id = 4,
                    userId = 1,
                    weekDay = "Monday",
                    date = "05.06.2023",
                    time = "14:00 - 14.30",
                    purpose = "Laboratory"
                ),
                Booking(
                    id = 5,
                    userId = 1,
                    weekDay = "Tuesday",
                    date = "06.06.2023",
                    time = "14:00 - 14.30",
                    purpose = "Meeting"
                ),
                Booking(
                    id = 6,
                    userId = 1,
                    weekDay = "Wednesday",
                    date = "07.06.2023",
                    time = "14:00 - 14.30",
                    purpose = "Party"
                ),
            )
        )
    }

    override suspend fun addNewRoom(
        userId: String,
        userToken: String,
        roomId: String,
        roomType: String,
        roomCapacity: String,
        roomFloor: String,
        roomDescription: String
    ): Long {
        return 200
    }

    override suspend fun deleteRoom(userId: String, userToken: String, roomId: String): String {
        return "Deleted"
    }
}