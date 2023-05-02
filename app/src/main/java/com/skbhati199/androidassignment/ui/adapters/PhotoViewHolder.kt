package com.skbhati199.androidassignment.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.skbhati199.androidassignment.R
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: ResponsePhotoItem) {
        binding.photo = photo
        Picasso.get().load(photo.thumbnailUrl)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.progress_animation )
            .into(binding.imageView)
        binding.executePendingBindings()
    }
}
