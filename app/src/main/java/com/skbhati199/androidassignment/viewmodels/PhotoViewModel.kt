package com.skbhati199.androidassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.respository.PhotoRepository
import com.skbhati199.androidassignment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel (
    private val repository: PhotoRepository
) : ViewModel() {

    sealed class PhotosEvent{
        class Success(val data:List<ResponsePhotoItem>):PhotosEvent()
        class Failure(val errorText:String):PhotosEvent()
        object Loading:PhotosEvent()
        object Empty:PhotosEvent()
    }

    private val _conversion = MutableStateFlow<PhotosEvent>(PhotosEvent.Empty)
    val conversion: StateFlow<PhotosEvent> = _conversion

    fun getPhotosViewModel(){
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = PhotosEvent.Loading
            when(val quotesResponse = repository.getPhotos()) {
                is Resource.Error -> _conversion.value = PhotosEvent.Failure(quotesResponse.message!!)
                is Resource.Success -> {
                    val photoModels = quotesResponse.data
                    if(photoModels == null) {
                        _conversion.value = PhotosEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = PhotosEvent.Success(photoModels)
                    }
                }
            }
        }
    }
}