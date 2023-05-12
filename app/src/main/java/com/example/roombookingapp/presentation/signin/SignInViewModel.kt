package com.example.roombookingapp.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.data.models.LoginResponse
import com.example.roombookingapp.domain.use_cases.UserLoginUseCase
import kotlinx.coroutines.launch

class SignInViewModel(private val userLoginUseCase: UserLoginUseCase) : ViewModel() {

    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loginStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val _userDataLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    val userDataLiveData: LiveData<LoginResponse> = _userDataLiveData

    init {
        progressLiveData.value = false
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                progressLiveData.value = true

                val response = userLoginUseCase(
                    email = emailLiveData.value.toString(),
                    password = passwordLiveData.value.toString()
                )

                _userDataLiveData.value = response

                loginStatusLiveData.value = response.jwtToken.isNotEmpty()

            } catch (e: Exception) {
                loginStatusLiveData.value = false
            } finally {
                progressLiveData.value = false
            }
        }
    }
}