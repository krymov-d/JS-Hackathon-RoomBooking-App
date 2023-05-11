package com.example.roombookingapp.presentation.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.utils.extensions.showToastLong
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomBookingFragment : Fragment() {

    private val vmRoomBooking: RoomBookingViewModel by viewModel()

    private lateinit var etReason: TextInputEditText
    private lateinit var etDate: TextInputEditText
    private lateinit var etStartTime: TextInputEditText
    private lateinit var etEndTime: TextInputEditText
    private lateinit var btnSelectDate: AppCompatButton
    private lateinit var btnSelectStartTime: AppCompatButton
    private lateinit var btnSelectEndTime: AppCompatButton
    private lateinit var btnSubmitRequest: AppCompatButton

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var onDateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var onTimeSetListener: TimePickerDialog.OnTimeSetListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListeners()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            etReason = findViewById(R.id.room_booking_et_reason)
            etDate = findViewById(R.id.room_booking_et_date)
            etStartTime = findViewById(R.id.room_booking_et_start_time)
            etEndTime = findViewById(R.id.room_booking_et_end_time)
            btnSelectDate = findViewById(R.id.room_booking_btn_select_date)
            btnSelectStartTime = findViewById(R.id.room_booking_btn_select_start_time)
            btnSelectEndTime = findViewById(R.id.room_booking_btn_select_end_time)
            btnSubmitRequest = findViewById(R.id.room_booking_btn_submit_request)
        }
    }

    private fun initClickListeners() {
        val currentContext = context ?: return

        etReason.addTextChangedListener {
            vmRoomBooking.reasonLiveData.value = it.toString()
        }

        btnSelectDate.setOnClickListener {
            initDateSetListener()
            showDatePickerDialog(currentContext)
        }

        btnSelectStartTime.setOnClickListener {
            initTimeSetListener(timeOption = 0)
            showTimePickerDialog(currentContext)
        }

        btnSelectEndTime.setOnClickListener {
            initTimeSetListener(timeOption = 1)
            showTimePickerDialog(currentContext)
        }

        btnSubmitRequest.setOnClickListener {
            if (vmRoomBooking.dateLiveData.value == null || vmRoomBooking.startLiveData.value == null || vmRoomBooking.endTimeLiveData.value == null) {
                currentContext.showToastLong(getString(R.string.please_fill_all_fields))
            } else {
                vmRoomBooking.submitBooking()
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun initDateSetListener() {
        onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthIndex, dayOfMonth ->
            vmRoomBooking.setDate(year = year, month = monthIndex + 1, day = dayOfMonth)
        }
    }

    private fun showDatePickerDialog(currentContext: Context) {
        datePickerDialog = DatePickerDialog(
            currentContext,
            onDateSetListener,
            vmRoomBooking.getCurrentYear(),
            vmRoomBooking.getCurrentMonth(),
            vmRoomBooking.getCurrentDay()
        )
        datePickerDialog.show()
    }

    private fun initTimeSetListener(timeOption: Int) {
        onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            when (timeOption) {
                0 -> vmRoomBooking.setStartTime(hour = hour, minute = minute)
                1 -> vmRoomBooking.setEndTime(hour = hour, minute = minute)
            }
        }
    }

    private fun showTimePickerDialog(currentContext: Context) {
        timePickerDialog = TimePickerDialog(
            currentContext,
            onTimeSetListener,
            vmRoomBooking.getCurrentHour(),
            vmRoomBooking.getCurrentMinute(),
            true
        )
        timePickerDialog.show()
    }

    private fun initObservers() {
        vmRoomBooking.dateLiveData.observe(viewLifecycleOwner) { pickedDate ->
            etDate.setText(pickedDate)
        }

        vmRoomBooking.startLiveData.observe(viewLifecycleOwner) { pickedStartTime ->
            etStartTime.setText(pickedStartTime)
        }

        vmRoomBooking.endTimeLiveData.observe(viewLifecycleOwner) { pickedEndTime ->
            etEndTime.setText(pickedEndTime)
        }
    }
}