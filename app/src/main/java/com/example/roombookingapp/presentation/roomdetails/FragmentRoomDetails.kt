package com.example.roombookingapp.presentation.roomdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.roombookingapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

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

    private lateinit var tvRoomId: TextView
    private lateinit var tvRoomCategory: TextView
    private lateinit var tvRoomFloor: TextView
    private lateinit var tvRoomCapacity: TextView
    private lateinit var tvRoomDescription: TextView

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
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            vpPhotos = findViewById(R.id.room_details_vp_photos)

            tvRoomId = findViewById(R.id.room_details_tv_id)
            tvRoomCategory = findViewById(R.id.room_details_tv_category)
            tvRoomFloor = findViewById(R.id.room_details_tv_floor)
            tvRoomCapacity = findViewById(R.id.room_details_tv_capacity)
            tvRoomDescription = findViewById(R.id.room_details_tv_description)
        }
    }

    private fun initPhotosViewPager() {
        photosViewPagerAdapter = PhotosViewPagerAdapter(inflater = layoutInflater)
        vpPhotos.adapter = photosViewPagerAdapter
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
        }
    }
}