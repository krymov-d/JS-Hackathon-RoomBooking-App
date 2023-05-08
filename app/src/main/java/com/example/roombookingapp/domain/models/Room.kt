package com.example.roombookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val id: Long,
    val category: String,
    val floor: Long,
    val capacity: Long,
    val description: String,
    val photos: Array<Byte>,
    val bookedTimeList: List<BookedTime>,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Room

        if (id != other.id) return false
        if (category != other.category) return false
        if (floor != other.floor) return false
        if (capacity != other.capacity) return false
        if (description != other.description) return false
        if (!photos.contentEquals(other.photos)) return false
        if (bookedTimeList != other.bookedTimeList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + floor.hashCode()
        result = 31 * result + capacity.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + photos.contentHashCode()
        result = 31 * result + bookedTimeList.hashCode()
        return result
    }
}