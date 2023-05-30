package com.example.roombookingapp.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.domain.use_cases.UserSignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(private val userSignInUseCase: UserSignInUseCase) : ViewModel() {

    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loginStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val _userDataLiveData: MutableLiveData<SignInResponse> = MutableLiveData()
    val userDataLiveData: LiveData<SignInResponse> = _userDataLiveData

    init {
        progressLiveData.value = false
    }

    fun userSignIn() {
        viewModelScope.launch {
            try {
                progressLiveData.value = true

                val response = userSignInUseCase(
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