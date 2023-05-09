package com.example.roombookingapp.presentation.roomdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.use_cases.GetRoomBookingsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.GetRoomPhotosUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val roomId: Long,
    private val getRoomPhotosUseCase: GetRoomPhotosUseCase,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
    private val getRoomBookingsUseCase: GetRoomBookingsUseCase,
) : ViewModel() {

    private val _roomPhotosLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val roomPhotosLiveData: LiveData<List<String>> = _roomPhotosLiveData

    private val _roomDetailsLiveData: MutableLiveData<RoomDetails> = MutableLiveData()
    val roomDetailsLiveData: LiveData<RoomDetails> = _roomDetailsLiveData

    private val _roomBookingsLiveData: MutableLiveData<List<Booking>> = MutableLiveData()
    val roomBookingsLiveData: LiveData<List<Booking>> = _roomBookingsLiveData

    init {
        viewModelScope.launch {
            val roomPhotos = getRoomPhotosUseCase(roomId = roomId)
            _roomPhotosLiveData.postValue(roomPhotos)
            val roomDetails = getRoomDetailsUseCase(roomId = roomId)
            _roomDetailsLiveData.postValue(roomDetails)
            val roomBookings = getRoomBookingsUseCase(roomId = roomId)
            _roomBookingsLiveData.postValue(roomBookings)
        }
    }
}