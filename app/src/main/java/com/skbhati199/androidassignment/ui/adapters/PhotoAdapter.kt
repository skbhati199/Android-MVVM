package com.skbhati199.androidassignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.databinding.ItemPhotoBinding

class PhotoAdapter(var photos: List<ResponsePhotoItem>?, val listener:OnItemClickListener) : RecyclerView.Adapter<PhotoViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ResponsePhotoItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        photos?.get(position)?.let { data->
            holder.bind(data)
            holder.itemView.setOnClickListener {
                listener.onItemClick(data, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    fun updatePhotoList(data: List<ResponsePhotoItem>) {
        photos = data
        notifyDataSetChanged()
    }
}