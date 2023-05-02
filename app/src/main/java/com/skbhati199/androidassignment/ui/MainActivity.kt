package com.skbhati199.androidassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.skbhati199.androidassignment.api.PhotoService
import com.skbhati199.androidassignment.databinding.ActivityMainBinding
import com.skbhati199.androidassignment.respository.PhotoRepository
import com.skbhati199.androidassignment.ui.adapters.PhotoAdapter
import com.skbhati199.androidassignment.viewmodels.PhotoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)




    }
}