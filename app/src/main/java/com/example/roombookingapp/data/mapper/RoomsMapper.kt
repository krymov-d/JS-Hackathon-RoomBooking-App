package com.example.roombookingapp.data.mapper

import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.domain.models.Room

fun toRoomsList(remoteRooms: List<RemoteRoom>): List<Room> {
    return remoteRooms.map { remoteRoom ->
        Room(id = remoteRoom.id, category = remoteRoom.type, photoUrl = remoteRoom.photos[0].name)
    }
}