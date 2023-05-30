package com.example.roombookingapp.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.use_cases.UserSignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(private val userSignUpUseCase: UserSignUpUseCase) : ViewModel() {

    val nameLiveData: MutableLiveData<String> = MutableLiveData()
    val surnameLiveData: MutableLiveData<String> = MutableLiveData()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val registrationStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        progressLiveData.value = false
    }

    fun registerUser() {
        viewModelScope.launch {
            try {
                progressLiveData.value = true

                val response = userSignUpUseCase(
                    name = nameLiveData.value.toString(),
                    surname = surnameLiveData.value.toString(),
                    email = emailLiveData.value.toString(),
                    password = passwordLiveData.value.toString()
                )

                registrationStatusLiveData.value = response == 200
            } catch (e: Exception) {
                registrationStatusLiveData.value = false
            } finally {
                progressLiveData.value = false
            }
        }
    }
}