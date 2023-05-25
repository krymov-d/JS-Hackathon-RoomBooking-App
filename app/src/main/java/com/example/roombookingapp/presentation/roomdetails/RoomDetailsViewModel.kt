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
import java.lang.Exception

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

    private val _removeBookingStatusLiveData: MutableLiveData<Int> = MutableLiveData()
    val removeBookingStatusLiveData: LiveData<Int> = _removeBookingStatusLiveData

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
            try {
                val response = removeBookingUseCase(
                    bookingId = _bookingsLiveData.value?.get(index)?.id.toString(),
                    roomId = roomId,
                    userId = userId,
                    userToken = userToken
                )
                if (response.isNotEmpty()) {
                    _removeBookingStatusLiveData.value = 0
                }
            } catch (e: Exception) {
                _removeBookingStatusLiveData.value = 1
            } finally {
                _removeBookingStatusLiveData.value = 2
            }
        }
    }
}