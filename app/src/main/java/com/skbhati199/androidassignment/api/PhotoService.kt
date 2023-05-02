package com.skbhati199.androidassignment.api

import com.skbhati199.androidassignment.data.ResponsePhotoItem
import retrofit2.Response
import retrofit2.http.GET

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(): Response<ArrayList<ResponsePhotoItem>>
}