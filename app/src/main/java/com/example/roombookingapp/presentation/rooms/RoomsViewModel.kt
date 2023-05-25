package com.example.roombookingapp.presentation.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.use_cases.DeleteRoomUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomsViewModel(
    private val userId: Long,
    private val userRole: String,
    private val userToken: String,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase
) : ViewModel() {

    private val _roomsLiveData: MutableLiveData<List<Room>> = MutableLiveData()
    val roomsLiveData: LiveData<List<Room>> = _roomsLiveData

    private val _isUserAdmin: MutableLiveData<Boolean> = MutableLiveData(false)
    val isUserAdmin: LiveData<Boolean> = _isUserAdmin

    val deleteRoomStatusLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        verifyUserRole()
        getRooms()
    }

    private fun verifyUserRole() {
        if (userRole == "ADMIN") {
            _isUserAdmin.value = true
        }
    }

    fun getRooms() {
        viewModelScope.launch {
            val rooms = getRoomsUseCase(
                userId = userId,
                userToken = userToken
            )
            _roomsLiveData.postValue(rooms)
        }
    }

    fun deleteRoom(roomId: Long) {
        viewModelScope.launch {
            try {
                val response = deleteRoomUseCase(
                    roomId = roomId.toString(),
                    userId = userId.toString(),
                    userToken = userToken
                )
                if (response.isNotEmpty()) {
                    deleteRoomStatusLiveData.value = 0
                }
            } catch (e: Exception) {
                deleteRoomStatusLiveData.value = 1
            } finally {
                deleteRoomStatusLiveData.value = 2
            }
        }
    }
}