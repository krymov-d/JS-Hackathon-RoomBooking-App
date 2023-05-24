package com.example.roombookingapp.presentation.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.constants.TAG_ROOM_ID
import com.example.roombookingapp.constants.TAG_USER_ID
import com.example.roombookingapp.constants.TAG_USER_TOKEN
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import com.example.roombookingapp.presentation.utils.extensions.showSnackBarWithAction
import com.example.roombookingapp.presentation.utils.extensions.showToastLong
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RoomBookingFragment : Fragment() {

    companion object {
        fun newInstance(userID: String, userToken: String, roomId: String): RoomBookingFragment {
            val args = Bundle()
            args.putString(TAG_USER_ID, userID)
            args.putString(TAG_USER_TOKEN, userToken)
            args.putString(TAG_ROOM_ID, roomId)

            val fragment = RoomBookingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val vmRoomBooking: RoomBookingViewModel by viewModel {
        val userID = arguments?.getString(TAG_USER_ID)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        val roomId = arguments?.getString(TAG_ROOM_ID)
        parametersOf(userID, userToken, roomId)
    }

    private lateinit var tbRoomBooking: Toolbar

    private lateinit var etReason: TextInputEditText
    private lateinit var tvDate: AppCompatTextView
    private lateinit var tvStartTime: AppCompatTextView
    private lateinit var tvEndTime: AppCompatTextView
    private lateinit var btnSelectDate: AppCompatButton
    private lateinit var btnSelectStartTime: AppCompatButton
    private lateinit var btnSelectEndTime: AppCompatButton
    private lateinit var btnSubmitRequest: AppCompatButton
    private lateinit var progressIndicator: CircularProgressIndicator

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

        val currentContext = context ?: return

        initViews(view)
        initToolbar()
        initClickListeners()
        initObservers(currentContext)
    }

    private fun initViews(view: View) {
        with(view) {
            tbRoomBooking = findViewById(R.id.room_booking_toolbar)
            etReason = findViewById(R.id.room_booking_et_reason)
            tvDate = findViewById(R.id.room_booking_tv_date)
            tvStartTime = findViewById(R.id.room_booking_tv_start_time)
            tvEndTime = findViewById(R.id.room_booking_tv_end_time)
            btnSelectDate = findViewById(R.id.room_booking_btn_select_date)
            btnSelectStartTime = findViewById(R.id.room_booking_btn_select_start_time)
            btnSelectEndTime = findViewById(R.id.room_booking_btn_select_end_time)
            btnSubmitRequest = findViewById(R.id.room_booking_btn_submit_request)
            progressIndicator = findViewById(R.id.room_booking_progress_indicator)
        }
    }

    private fun initToolbar() {
        tbRoomBooking.title = getString(R.string.room_booking)
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

        tvDate.setOnClickListener {
            initDateSetListener()
            showDatePickerDialog(currentContext)
        }

        tvStartTime.setOnClickListener {
            initTimeSetListener(timeOption = 0)
            showTimePickerDialog(currentContext)
        }

        tvEndTime.setOnClickListener {
            initTimeSetListener(timeOption = 1)
            showTimePickerDialog(currentContext)
        }

        btnSubmitRequest.setOnClickListener {
            if (etReason.text.toString().isEmpty()
                || tvDate.text.isEmpty()
                || tvStartTime.text.isEmpty()
                || tvEndTime.text.isEmpty()
            ) {
                currentContext.showToastLong(getString(R.string.please_fill_all_fields))
            } else {
                vmRoomBooking.addNewBooking()
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

    private fun initObservers(currentContext: Context) {
        vmRoomBooking.dateLiveData.observe(viewLifecycleOwner) { pickedDate ->
            tvDate.text = pickedDate
        }

        vmRoomBooking.startLiveData.observe(viewLifecycleOwner) { pickedStartTime ->
            tvStartTime.text = pickedStartTime
        }

        vmRoomBooking.endTimeLiveData.observe(viewLifecycleOwner) { pickedEndTime ->
            tvEndTime.text = pickedEndTime
        }

        vmRoomBooking.progressLiveData.observe(viewLifecycleOwner) { isInProgress ->
            if (isInProgress) {
                btnSubmitRequest.isEnabled = false
                btnSubmitRequest.setTextColor(resources.getColor(R.color.ui_03, null))
                progressIndicator.visibility = View.VISIBLE
            } else {
                btnSubmitRequest.isEnabled = true
                btnSubmitRequest.setTextColor(resources.getColor(R.color.ui_01, null))
                progressIndicator.visibility = View.INVISIBLE
            }
        }

        vmRoomBooking.submitStatusLiveData.observe(viewLifecycleOwner) { isSubmitted ->
            if (isSubmitted) {
                currentContext.showSnackBar(
                    view = btnSubmitRequest,
                    messageStringId = R.string.booking_successful
                )

                parentFragmentManager.popBackStack()

            } else {
                currentContext.showSnackBarWithAction(
                    view = btnSubmitRequest,
                    messageStringId = R.string.booking_failed,
                    actionStringId = R.string.retry
                ) {
                    vmRoomBooking.addNewBooking()
                }
            }
        }
    }
}