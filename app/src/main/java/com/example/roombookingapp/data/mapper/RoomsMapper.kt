package com.example.roombookingapp.data.mapper

import com.example.roombookingapp.data.models.RemoteRoom
import com.example.roombookingapp.domain.models.Room

fun toRoomsList(remoteRooms: List<RemoteRoom>): List<Room> {
    return remoteRooms.map { remoteRoom ->
        val photoUrlList = remoteRoom.photos.map { remotePhoto ->
            remotePhoto.name
        }
        Room(id = remoteRoom.id, category = remoteRoom.type, photoUrlList = photoUrlList)
    }
}