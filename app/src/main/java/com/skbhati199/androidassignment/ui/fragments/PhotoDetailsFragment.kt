package com.skbhati199.androidassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.skbhati199.androidassignment.R
import com.skbhati199.androidassignment.databinding.FragmentPhotoDetailsBinding
import com.skbhati199.androidassignment.utils.CircleTransform
import com.squareup.picasso.Picasso

class PhotoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: PhotoDetailsFragmentArgs by navArgs()
        val photoItem = args.photoItem
        binding.photoItem = photoItem
        Picasso.get().load(photoItem.url)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.progress_animation )
            .into(binding.bgImage)
        Picasso.get().load(photoItem.thumbnailUrl)
            .transform(CircleTransform())
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.progress_animation )
            .into(binding.avatarIcon)
        binding.executePendingBindings()


    }


}