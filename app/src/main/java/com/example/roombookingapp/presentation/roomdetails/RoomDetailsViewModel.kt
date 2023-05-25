package com.example.roombookingapp.presentation.roomdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.Booking
import com.example.roombookingapp.domain.models.RoomDetails
import com.example.roombookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.roombookingapp.domain.use_cases.RemoveBookingUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val userId: String,
    private val userToken: String,
    private val roomId: String,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
    private val removeBookingUseCase: RemoveBookingUseCase,
) : ViewModel() {

    private val _roomDetailsLiveData: MutableLiveData<RoomDetails> = MutableLiveData()
    val roomDetailsLiveData: LiveData<RoomDetails> = _roomDetailsLiveData

    private val _bookingsLiveData: MutableLiveData<List<Booking>> = MutableLiveData()
    val bookingsLiveData: LiveData<List<Booking>> = _bookingsLiveData

    private val _removeBookingStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val removeBookingStatusLiveData: LiveData<Boolean> = _removeBookingStatusLiveData

    init {
        getRoomDetails()
    }

    fun getRoomDetails() {
        viewModelScope.launch {
            val roomDetails =
                getRoomDetailsUseCase(userId = userId, userToken = userToken, roomId = roomId)
            _roomDetailsLiveData.postValue(roomDetails)
            _bookingsLiveData.postValue(roomDetails.bookingList)
        }
    }

    fun deleteBooking(index: Int) {
        viewModelScope.launch {
            val response = removeBookingUseCase(
                bookingId = _bookingsLiveData.value?.get(index)?.id.toString(),
                roomId = roomId,
                userId = userId,
                userToken = userToken
            )
            _removeBookingStatusLiveData.postValue(response.isNotEmpty())
        }
    }
}