package com.example.roombookingapp.presentation.roomdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.roombookingapp.R
import com.example.roombookingapp.constants.TAG_ROOM_ID
import com.example.roombookingapp.constants.TAG_USER_ID
import com.example.roombookingapp.constants.TAG_USER_TOKEN
import com.example.roombookingapp.presentation.booking.RoomBookingFragment
import com.example.roombookingapp.presentation.roomdetails.bookings.BookingsAdapter
import com.example.roombookingapp.presentation.roomdetails.photos.PhotosViewPagerAdapter
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RoomDetailsFragment : Fragment() {

    companion object {
        fun newInstance(userID: String, userToken: String, roomId: String): RoomDetailsFragment {
            val args = Bundle()
            args.putString(TAG_USER_ID, userID)
            args.putString(TAG_USER_TOKEN, userToken)
            args.putString(TAG_ROOM_ID, roomId)
            val fragment = RoomDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val flContainerID = R.id.fl_login_container

    private val vmRoomDetails: RoomDetailsViewModel by viewModel {
        val userID = arguments?.getString(TAG_USER_ID)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        val roomId = arguments?.getString(TAG_ROOM_ID)
        parametersOf(userID, userToken, roomId)
    }

    private lateinit var userId: String
    private lateinit var userToken: String
    private lateinit var roomId: String

    private lateinit var tbRoomDetails: Toolbar
    private lateinit var vpPhotos: ViewPager2
    private lateinit var photosViewPagerAdapter: PhotosViewPagerAdapter

    private lateinit var tvRoomId: TextView
    private lateinit var tvBookThisRoom: TextView
    private lateinit var tvRoomCategory: TextView
    private lateinit var tvRoomFloor: TextView
    private lateinit var tvRoomCapacity: TextView
    private lateinit var tvRoomDescription: TextView

    private lateinit var rvBookings: RecyclerView
    private lateinit var bookingsAdapter: BookingsAdapter
    private lateinit var bookingLayoutManager: LinearLayoutManager
    private lateinit var bookingItemDecorator: SpaceItemDecoration
    private lateinit var bookingItemTouchHelper: ItemTouchHelper
    private lateinit var bookingItemTouchCallback: ItemTouchHelper.SimpleCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = arguments?.getString(TAG_USER_ID) ?: return
        userToken = arguments?.getString(TAG_USER_TOKEN) ?: return
        roomId = arguments?.getString(TAG_ROOM_ID) ?: return
    }

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
        initToolbar()
        initPhotosViewPager()
        initBookingsRecyclerView()
        initClickListener()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            tbRoomDetails = findViewById(R.id.room_details_toolbar)

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

    private fun initToolbar() {
        tbRoomDetails.title = getString(R.string.room_details)
    }

    private fun initPhotosViewPager() {
        photosViewPagerAdapter = PhotosViewPagerAdapter(inflater = layoutInflater)
        photosViewPagerAdapter.currentContext = context
        vpPhotos.adapter = photosViewPagerAdapter
    }

    private fun initBookingsRecyclerView() {
        bookingsAdapter = BookingsAdapter(inflater = layoutInflater, userId = userId.toLong())
        bookingLayoutManager = LinearLayoutManager(context)
        bookingItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        bookingItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN, START or END) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    vmRoomDetails.deleteBooking(index = viewHolder.layoutPosition)
                }
            }
        bookingItemTouchHelper = ItemTouchHelper(bookingItemTouchCallback)

        rvBookings.apply {
            adapter = bookingsAdapter
            layoutManager = bookingLayoutManager
            addItemDecoration(bookingItemDecorator)
        }
        bookingItemTouchHelper.attachToRecyclerView(rvBookings)
    }

    private fun initClickListener() {
        tvRoomId.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        tvBookThisRoom.setOnClickListener {
            initRoomBookingFragment()
        }
    }

    private fun initRoomBookingFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                flContainerID, RoomBookingFragment.newInstance(
                    userID = userId,
                    userToken = userToken,
                    roomId = roomId
                ), null
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initObservers() {
        vmRoomDetails.roomDetailsLiveData.observe(viewLifecycleOwner) { roomDetails ->
            photosViewPagerAdapter.setPhotos(roomDetails.photoUrlList)
            with(roomDetails) {
                tvRoomId.text = getString(R.string.room_w_column, id.toString())
                tvRoomCategory.text = category
                tvRoomFloor.text = floor.toString()
                tvRoomCapacity.text = capacity.toString()
                tvRoomDescription.text = description
            }
        }

        vmRoomDetails.bookingsLiveData.observe(viewLifecycleOwner) { bookings ->
            bookingsAdapter.submitList(bookings)
        }

        vmRoomDetails.removeBookingStatusLiveData.observe(viewLifecycleOwner) { isDeleted ->
            if (isDeleted) {
                context?.showSnackBar(
                    view = tvRoomId,
                    messageStringId = R.string.booking_is_deleted
                )
                vmRoomDetails.getRoomDetails()
            }
        }
    }
}