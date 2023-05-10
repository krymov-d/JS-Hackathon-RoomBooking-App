package com.example.roombookingapp.presentation.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class RoomBookingFragment : Fragment() {

    private lateinit var etReason: TextInputEditText
    private lateinit var etDate: TextInputEditText
    private lateinit var etStartTime: TextInputEditText
    private lateinit var etEndTime: TextInputEditText
    private lateinit var btnSelectDate: AppCompatButton
    private lateinit var btnSelectStartTime: AppCompatButton
    private lateinit var btnSelectEndTime: AppCompatButton
    private lateinit var btnSubmitRequest: AppCompatButton

    private lateinit var calendar: Calendar
    private var currentYear = 0
    private var currentMonth = 0
    private var currentDay = 0
    private var currentHour = 0
    private var currentMinute = 0

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
        initCalendar()
        initClickListeners()
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

    private fun initCalendar() {
        calendar = Calendar.getInstance()
    }

    private fun initClickListeners() {
        val currentContext = context ?: return
        btnSelectDate.setOnClickListener {
            setCurrentDate()
            setDateSetListener()
            showDatePickerDialog(currentContext)
        }

        btnSelectStartTime.setOnClickListener {
            setCurrentTime()
            setTimeSetListener(timeOption = 0)
            showTimePickerDialog(currentContext)
        }

        btnSelectEndTime.setOnClickListener {
            setCurrentTime()
            setTimeSetListener(timeOption = 1)
            showTimePickerDialog(currentContext)
        }

        btnSubmitRequest.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setCurrentDate() {
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun setDateSetListener() {
        onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthIndex, dayOfMonth ->
            val month = monthIndex + 1
            val date = String.format("%02d / %02d / %04d", dayOfMonth, month, year)
            etDate.setText(date)
        }
    }

    private fun showDatePickerDialog(currentContext: Context) {
        datePickerDialog = DatePickerDialog(
            currentContext,
            onDateSetListener,
            currentYear,
            currentMonth,
            currentDay
        )
        datePickerDialog.show()
    }

    private fun setCurrentTime() {
        currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        currentMinute = calendar.get(Calendar.MINUTE)
    }

    private fun setTimeSetListener(timeOption: Int) {
        onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            val time = String.format("%02d : %02d", hour, minute)
            when (timeOption) {
                0 -> etStartTime.setText(time)
                1 -> etEndTime.setText(time)
            }
        }
    }

    private fun showTimePickerDialog(currentContext: Context) {
        timePickerDialog = TimePickerDialog(
            currentContext,
            onTimeSetListener,
            currentHour,
            currentMinute,
            true
        )
        timePickerDialog.show()
    }
}