package com.example.roombookingapp.presentation.roomdetails.photos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.roombookingapp.R

class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivPhotoContainer: ImageView = itemView.findViewById(R.id.item_photo_iv_container)
    val tvPhotoQuantity: TextView = itemView.findViewById(R.id.item_photo_tv_quantity)

    fun bind(imageURL: String) {
        Glide.with(ivPhotoContainer)
            .load(imageURL)
            .error(R.drawable.iv_no_photography)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivPhotoContainer)
    }
}