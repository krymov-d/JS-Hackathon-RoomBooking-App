package com.example.roombookingapp.presentation.rooms.allusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.data.models.RemoteUser
import com.example.roombookingapp.domain.use_cases.GetAllUsersUseCase
import kotlinx.coroutines.launch

class AllUsersViewModel(
    private val userId: String,
    private val userToken: String,
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _usersLiveData: MutableLiveData<List<RemoteUser>> = MutableLiveData()
    val usersLiveData: LiveData<List<RemoteUser>> = _usersLiveData

    init {
        viewModelScope.launch {
            val users = getAllUsersUseCase(userId = userId, userToken = userToken)
            _usersLiveData.postValue(users)
        }
    }
}