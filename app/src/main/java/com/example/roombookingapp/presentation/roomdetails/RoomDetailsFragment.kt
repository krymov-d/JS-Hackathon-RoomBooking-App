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
import com.example.roombookingapp.presentation.roomdetails.bookings.BookingsAdapter
import com.example.roombookingapp.presentation.roomdetails.photos.PhotosViewPagerAdapter
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import com.example.roombookingapp.presentation.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val TAG_ROOM_ID = "TAG_ROOM_ID"

class RoomDetailsFragment : Fragment() {

    companion object {
        fun newInstance(roomId: Long): RoomDetailsFragment {
            val args = Bundle()
            args.putLong(TAG_ROOM_ID, roomId)
            val fragment = RoomDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val vmRoomDetails: RoomDetailsViewModel by viewModel {
        parametersOf(arguments?.getLong(TAG_ROOM_ID) ?: 0)
    }

    private lateinit var vpPhotos: ViewPager2
    private lateinit var photosViewPagerAdapter: PhotosViewPagerAdapter

    private lateinit var tvBookThisRoom: TextView

    private lateinit var tvRoomId: TextView
    private lateinit var tvRoomCategory: TextView
    private lateinit var tvRoomFloor: TextView
    private lateinit var tvRoomCapacity: TextView
    private lateinit var tvRoomDescription: TextView

    private lateinit var rvBookings: RecyclerView
    private lateinit var bookingsAdapter: BookingsAdapter
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

            tvBookThisRoom = findViewById(R.id.room_details_tv_book)

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
        photosViewPagerAdapter.currentContext = context
        vpPhotos.adapter = photosViewPagerAdapter
    }

    private fun initBookingsRecyclerView() {
        bookingsAdapter = BookingsAdapter(inflater = layoutInflater)
        bookingLayoutManager = LinearLayoutManager(context)
        bookingItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)

        rvBookings.apply {
            adapter = bookingsAdapter
            layoutManager = bookingLayoutManager
            addItemDecoration(bookingItemDecorator)
        }
    }

    private fun initClickListener() {
        tvRoomId.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        tvBookThisRoom.setOnClickListener {
            context?.showToast("Fragment Booking")
        }
    }

    private fun initObservers() {
        vmRoomDetails.roomPhotosLiveData.observe(viewLifecycleOwner) { roomPhotos ->
            photosViewPagerAdapter.setPhotos(roomPhotos)
        }

        vmRoomDetails.roomDetailsLiveData.observe(viewLifecycleOwner) { roomDetails ->
            with(roomDetails) {
                tvRoomId.text = getString(R.string.room_w_column, id.toString())
                tvRoomCategory.text = category
                tvRoomFloor.text = floor.toString()
                tvRoomCapacity.text = capacity.toString()
                tvRoomDescription.text = description
            }
        }

        vmRoomDetails.roomBookingsLiveData.observe(viewLifecycleOwner) { roomBookings ->
            bookingsAdapter.submitList(roomBookings)
        }
    }
}