package com.example.roombookingapp.presentation.roomdetails.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R

class PhotosViewPagerAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<PhotosViewHolder>() {

    private val data: MutableList<String> = mutableListOf()
    var currentContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView = inflater.inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(data[position])
        holder.tvPhotoQuantity.text =
            currentContext?.getString(R.string.room_photo_quantity, position + 1, data.size)
    }

    fun setPhotos(photoUrlList: List<String>) {
        data.clear()
        data.addAll(photoUrlList)
        notifyItemRangeChanged(0, data.size)
    }
}