package com.example.roombookingapp.presentation.allusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.User
import com.example.roombookingapp.domain.use_cases.GetAllUsersUseCase
import com.example.roombookingapp.domain.use_cases.MakeAdminUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class AllUsersViewModel(
    private val userId: String,
    private val userToken: String,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val makeAdminUseCase: MakeAdminUseCase
) : ViewModel() {

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    val makeAdminStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = getAllUsersUseCase(userId = userId, userToken = userToken)
            _usersLiveData.postValue(users)
        }
    }

    fun makeAdmin(id: Long) {
        viewModelScope.launch {
            try {
                val response = makeAdminUseCase(userId = id.toString(), userToken = userToken)
                makeAdminStatusLiveData.postValue(response.role == "ADMIN")
            } catch (e: Exception) {
                makeAdminStatusLiveData.postValue(false)
            }
        }
    }
}