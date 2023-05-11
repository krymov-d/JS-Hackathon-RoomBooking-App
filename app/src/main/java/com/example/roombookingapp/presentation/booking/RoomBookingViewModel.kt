package com.example.roombookingapp.presentation.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombookingapp.domain.use_cases.SubmitBookingUseCase
import kotlinx.coroutines.launch
import java.util.Calendar

class RoomBookingViewModel(private val submitBookingUseCase: SubmitBookingUseCase) : ViewModel() {

    private val calendar = Calendar.getInstance()

    private var pickedDay = 0
    private var pickedMonth = 0
    private var pickedYear = 0
    private var pickedStartHour = 0
    private var pickedStartMinute = 0
    private var pickedEndHour = 0
    private var pickedEndMinute = 0

    val reasonLiveData: MutableLiveData<String> = MutableLiveData()

    private val _dateLiveData: MutableLiveData<String> = MutableLiveData()
    val dateLiveData: LiveData<String> = _dateLiveData

    private val _startTimeLiveData: MutableLiveData<String> = MutableLiveData()
    val startLiveData: LiveData<String> = _startTimeLiveData

    private val _endTimeLiveData: MutableLiveData<String> = MutableLiveData()
    val endTimeLiveData: LiveData<String> = _endTimeLiveData

    fun setDate(year: Int, month: Int, day: Int) {
        pickedYear = year
        pickedMonth = month
        pickedDay = day
        _dateLiveData.value = String.format("%02d / %02d / %04d", day, month, year)
    }

    fun setStartTime(hour: Int, minute: Int) {
        pickedStartHour = hour
        pickedStartMinute = minute
        _startTimeLiveData.value = String.format("%02d : %02d", hour, minute)
    }

    fun setEndTime(hour: Int, minute: Int) {
        pickedEndHour = hour
        pickedEndMinute = minute
        _endTimeLiveData.value = String.format("%02d : %02d", hour, minute)
    }

    fun getCurrentYear() = calendar.get(Calendar.YEAR)
    fun getCurrentMonth() = calendar.get(Calendar.MONTH)
    fun getCurrentDay() = calendar.get(Calendar.DAY_OF_MONTH)
    fun getCurrentHour() = calendar.get(Calendar.HOUR_OF_DAY)
    fun getCurrentMinute() = calendar.get(Calendar.MINUTE)

    fun submitBooking() {
        viewModelScope.launch {
            val reason = reasonLiveData.value ?: ""
            submitBookingUseCase(
                reason = reason,
                day = pickedDay,
                month = pickedMonth,
                year = pickedYear,
                startHour = pickedStartHour,
                startMinute = pickedStartMinute,
                endHour = pickedEndHour,
                endMinute = pickedEndMinute
            )
        }
    }
}