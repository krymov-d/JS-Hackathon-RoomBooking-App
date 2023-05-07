package com.example.roombookingapp.presentation.roomdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R

class PhotosViewPagerAdapter(
    private val imageURLList: List<String>,
    private val inflater: LayoutInflater
) :
    RecyclerView.Adapter<PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView = inflater.inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imageURLList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(imageURLList[position])
    }
}