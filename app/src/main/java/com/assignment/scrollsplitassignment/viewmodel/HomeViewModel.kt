package com.assignment.scrollsplitassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.assignment.scrollsplitassignment.viewmodel.repository.HomeRepository
import com.assignment.scrollsplitassignment.viewmodel.requestResponse.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    /*API Request*/
    suspend fun getDataList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getDataList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}