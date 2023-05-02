package com.skbhati199.androidassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.databinding.FragmentPhotosBinding
import com.skbhati199.androidassignment.ui.adapters.PhotoAdapter
import com.skbhati199.androidassignment.viewmodels.PhotoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel by viewModel<PhotoViewModel>()
    private lateinit var binding : FragmentPhotosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        photoAdapter = PhotoAdapter(mutableListOf(), object : PhotoAdapter.OnItemClickListener{
            override fun onItemClick(item: ResponsePhotoItem, position: Int) {
                val action = PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailsFragment(item)
                navController.navigate(action)
            }
        })
        binding.viewModel = viewModel
        binding.photoList.adapter = photoAdapter

        viewModel.getPhotosViewModel()

        lifecycleScope.launch {
            viewModel.conversion.collect{value : PhotoViewModel.PhotosEvent ->
                when(value){
                    is PhotoViewModel.PhotosEvent.Success -> {
                        binding.progressBar.visibility = View.GONE
                        photoAdapter.updatePhotoList(value.data)
                    }
                    is PhotoViewModel.PhotosEvent.Failure -> {
                        binding.progressBar.visibility =View.GONE
                        Toast.makeText(context, "${value.errorText}", Toast.LENGTH_SHORT).show()
                    }
                    is PhotoViewModel.PhotosEvent.Loading -> {
                        binding.progressBar.visibility =View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }

    }


}