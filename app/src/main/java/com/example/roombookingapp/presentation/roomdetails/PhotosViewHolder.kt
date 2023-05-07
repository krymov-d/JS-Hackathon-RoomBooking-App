package com.example.roombookingapp.presentation.roomdetails

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.roombookingapp.R

class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivPhotoContainer: ImageView = itemView.findViewById(R.id.item_photo_iv_container)

    fun bind(imageURL: String) {
        Log.d("RoomDetails", imageURL)
        Glide.with(ivPhotoContainer).load(imageURL).error(R.drawable.ic_launcher_main_foreground)
            .transition(DrawableTransitionOptions.withCrossFade()).into(ivPhotoContainer)
    }
}