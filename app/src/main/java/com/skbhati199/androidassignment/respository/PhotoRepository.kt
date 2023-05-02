package com.skbhati199.androidassignment.respository

import android.util.Log
import com.skbhati199.androidassignment.api.PhotoService
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.utils.Resource

private const val TAG = "PhotoRepository"

class PhotoRepository(
    private val photoApi: PhotoService
)  {
    suspend fun getPhotos(): Resource<ArrayList<ResponsePhotoItem>> {
        return try {
            val response = photoApi.getPhotos()
            Log.d(TAG, "getPhotos: ${response.body()}")
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to fetch photos")
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}