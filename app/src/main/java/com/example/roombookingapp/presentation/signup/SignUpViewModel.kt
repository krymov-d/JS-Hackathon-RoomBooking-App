package com.example.roombookingapp.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.use_cases.RegisterUserUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    val nameLiveData: MutableLiveData<String> = MutableLiveData()
    val surnameLiveData: MutableLiveData<String> = MutableLiveData()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val registrationStatus: MutableLiveData<Boolean> = MutableLiveData()

    init {
        progressLiveData.value = false
    }

    fun registerUser() {
        viewModelScope.launch {
            try {
                progressLiveData.value = true
                val response = registerUserUseCase(
                    name = nameLiveData.value.toString(),
                    surname = surnameLiveData.value.toString(),
                    email = emailLiveData.value.toString(),
                    password = passwordLiveData.value.toString()
                )
                registrationStatus.value = response.toString().isNotEmpty()
            } catch (e: Exception) {
                progressLiveData.value = false
                registrationStatus.value = false
            }
        }
    }
}