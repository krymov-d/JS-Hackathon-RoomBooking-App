package com.example.roombookingapp.presentation.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.Room
import com.example.roombookingapp.domain.use_cases.GetRoomsUseCase
import kotlinx.coroutines.launch

class RoomsViewModel(private val getRoomsUseCase: GetRoomsUseCase) : ViewModel() {

    private val _roomsLiveData: MutableLiveData<List<Room>> = MutableLiveData()
    val roomsLiveData: LiveData<List<Room>> = _roomsLiveData

    init {
        viewModelScope.launch {
            val rooms = getRoomsUseCase()
            _roomsLiveData.postValue(rooms)
        }
    }
}