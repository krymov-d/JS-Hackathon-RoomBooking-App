package com.example.roombookingapp.presentation.addroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.use_cases.AddNewRoomUseCase
import kotlinx.coroutines.launch

class AddNewRoomViewModel(
    private val userId: String,
    private val userToken: String,
    private val addNewRoomUseCase: AddNewRoomUseCase
) : ViewModel() {

    val roomIdLiveData: MutableLiveData<String> = MutableLiveData()
    val roomTypeLiveData: MutableLiveData<String> = MutableLiveData()
    val roomCapacityLiveData: MutableLiveData<String> = MutableLiveData()
    val roomFloorLiveData: MutableLiveData<String> = MutableLiveData()
    val roomDescriptionLiveData: MutableLiveData<String> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val roomCreateStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        progressLiveData.value = false
    }

    fun createRoom() {
        viewModelScope.launch {
            try {
                progressLiveData.value = true

                val response = addNewRoomUseCase(
                    userId = userId,
                    userToken = userToken,
                    roomId = roomIdLiveData.value.toString(),
                    roomType = roomTypeLiveData.value.toString(),
                    roomCapacity = roomCapacityLiveData.value.toString(),
                    roomFloor = roomFloorLiveData.value.toString(),
                    roomDescription = roomDescriptionLiveData.value.toString()
                )

                roomCreateStatusLiveData.value = response != -1L

            } catch (e: Exception) {
                roomCreateStatusLiveData.value = false
            } finally {
                progressLiveData.value = false
            }
        }
    }
}