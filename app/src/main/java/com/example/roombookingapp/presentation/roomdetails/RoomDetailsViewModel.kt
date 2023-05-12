package com.example.roombookingapp.presentation.roomdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val userId: String,
    private val userToken: String,
    private val roomId: String,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
) : ViewModel() {

    private val _roomDetailsLiveData: MutableLiveData<RoomDetails> = MutableLiveData()
    val roomDetailsLiveData: LiveData<RoomDetails> = _roomDetailsLiveData

    init {
        viewModelScope.launch {
            val roomDetails =
                getRoomDetailsUseCase(userId = userId, userToken = userToken, roomId = roomId)
            _roomDetailsLiveData.value = roomDetails
        }
    }
}