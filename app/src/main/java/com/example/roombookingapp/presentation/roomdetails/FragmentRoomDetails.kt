package com.example.roombookingapp.presentation.roomdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import com.example.roombookingapp.presentation.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG_ROOM_ID = "TAG_ROOM_ID"

class FragmentRoomDetails : Fragment() {

    companion object {
        fun newInstance(roomId: Long): FragmentRoomDetails {
            val args = Bundle()
            args.putLong(TAG_ROOM_ID, roomId)
            val fragment = FragmentRoomDetails()
            fragment.arguments = args
            return fragment
        }
    }

    private val vmRoomDetails: RoomDetailsViewModel by viewModel {
        parametersOf(arguments?.getLong(TAG_ROOM_ID) ?: 0)
    }

    private lateinit var vpPhotos: ViewPager2
    private lateinit var photosViewPagerAdapter: PhotosViewPagerAdapter

    private lateinit var tvBook: TextView

    private lateinit var tvRoomId: TextView
    private lateinit var tvRoomCategory: TextView
    private lateinit var tvRoomFloor: TextView
    private lateinit var tvRoomCapacity: TextView
    private lateinit var tvRoomDescription: TextView

    private lateinit var rvBookings: RecyclerView
    private lateinit var bookingAdapter: BookingAdapter
    private lateinit var bookingLayoutManager: LinearLayoutManager
    private lateinit var bookingItemDecorator: SpaceItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initPhotosViewPager()
        initBookingsRecyclerView()
        initClickListener()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            vpPhotos = findViewById(R.id.room_details_vp_photos)

            tvBook = findViewById(R.id.room_details_tv_book)

            tvRoomId = findViewById(R.id.room_details_tv_id)
            tvRoomCategory = findViewById(R.id.room_details_tv_category)
            tvRoomFloor = findViewById(R.id.room_details_tv_floor)
            tvRoomCapacity = findViewById(R.id.room_details_tv_capacity)
            tvRoomDescription = findViewById(R.id.room_details_tv_description)

            rvBookings = findViewById(R.id.room_details_rv_bookings)
        }
    }

    private fun initPhotosViewPager() {
        photosViewPagerAdapter = PhotosViewPagerAdapter(inflater = layoutInflater)
        vpPhotos.adapter = photosViewPagerAdapter
    }

    private fun initBookingsRecyclerView() {
        bookingAdapter = BookingAdapter(inflater = layoutInflater)
        bookingLayoutManager = LinearLayoutManager(context)
        bookingItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)

        rvBookings.apply {
            adapter = bookingAdapter
            layoutManager = bookingLayoutManager
            addItemDecoration(bookingItemDecorator)
        }
    }

    private fun initClickListener() {
        tvRoomId.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        tvBook.setOnClickListener {
            context?.showToast("Fragment Booking")
        }
    }

    private fun initObservers() {
        vmRoomDetails.roomsLiveData.observe(viewLifecycleOwner) {
            with(it) {
                photosViewPagerAdapter.setPhotos(photos)

                tvRoomId.text = getString(R.string.room_w_column, id.toString())
                tvRoomCategory.text = category
                tvRoomFloor.text = floor.toString()
                tvRoomCapacity.text = capacity.toString()
                tvRoomDescription.text = description
            }


            val bookedTime = it.bookedTimeList[0]
            val period = bookedTime.period

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz")
            val timeFormatter = DateTimeFormatter.ofPattern("hh:mm")
            val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

            val dateTimeStart = LocalDateTime.parse(period.startTime, formatter)
            val dateTimeEnd = LocalDateTime.parse(period.endTime, formatter)

            val formattedDate = dateTimeStart.format(dateFormatter)
            val formattedTimeStart = dateTimeStart.format(timeFormatter)
            val formattedTimeEnd = dateTimeEnd.format(timeFormatter)

            val listOfBookings = listOf(
                Booking(
                    id = 0,
                    date = formattedDate,
                    time = "$formattedTimeStart - $formattedTimeEnd",
                    purpose = bookedTime.purpose
                ),
                Booking(
                    id = 1,
                    date = formattedDate,
                    time = "$formattedTimeStart - $formattedTimeEnd",
                    purpose = bookedTime.purpose
                ),
                Booking(
                    id = 2,
                    date = formattedDate,
                    time = "$formattedTimeStart - $formattedTimeEnd",
                    purpose = bookedTime.purpose
                ),
                Booking(
                    id = 3,
                    date = formattedDate,
                    time = "$formattedTimeStart - $formattedTimeEnd",
                    purpose = bookedTime.purpose
                ),
                Booking(
                    id = 4,
                    date = formattedDate,
                    time = "$formattedTimeStart - $formattedTimeEnd",
                    purpose = bookedTime.purpose
                )
            )

            bookingAdapter.submitList(listOfBookings)
        }
    }
}