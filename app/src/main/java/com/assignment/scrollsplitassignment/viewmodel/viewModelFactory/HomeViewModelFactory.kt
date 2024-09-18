package com.assignment.scrollsplitassignment.viewmodel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.scrollsplitassignment.viewmodel.HomeViewModel
import com.assignment.scrollsplitassignment.viewmodel.repository.HomeRepository

class HomeViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}