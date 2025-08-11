package com.example.prueba_tecnica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prueba_tecnica.data.network.WebService

class ResponseViewModelFactory(
    private val api: WebService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResponseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResponseViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}