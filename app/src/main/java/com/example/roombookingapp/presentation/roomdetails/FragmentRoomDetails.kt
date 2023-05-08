package com.example.roombookingapp.presentation.roomdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.roombookingapp.R

class FragmentRoomDetails : Fragment() {

    private var imageUrlList = listOf<String>()

    private lateinit var vpPhotos: ViewPager2
    private lateinit var photosViewPagerAdapter: PhotosViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImageURL()

        initViews(view)
        initPhotosViewPagerProperties()
        initPhotosViewPager()
    }

    private fun initImageURL() {
        imageUrlList = listOf(
            "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
        )
    }

    private fun initViews(view: View) {
        with(view) {
            vpPhotos = findViewById(R.id.room_details_vp_photos)
        }
    }

    private fun initPhotosViewPagerProperties() {
        initPhotosViewPagerAdapter()
    }

    private fun initPhotosViewPagerAdapter() {
        photosViewPagerAdapter = PhotosViewPagerAdapter(imageUrlList, layoutInflater)
    }

    private fun initPhotosViewPager() {
        vpPhotos.adapter = photosViewPagerAdapter
    }
}