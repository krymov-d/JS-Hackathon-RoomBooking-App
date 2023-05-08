package com.example.roombookingapp.presentation.roomdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val roomId: Long,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase
) : ViewModel() {

    private val _roomsLiveData: MutableLiveData<Room> = MutableLiveData()
    val roomsLiveData: LiveData<Room> = _roomsLiveData

    init {
        viewModelScope.launch {
            val roomDetails = getRoomDetailsUseCase(roomId = roomId)
            _roomsLiveData.postValue(roomDetails)
        }
    }
}