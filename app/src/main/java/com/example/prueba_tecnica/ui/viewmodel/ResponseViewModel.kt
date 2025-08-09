package com.example.prueba_tecnica.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.data.network.WebService
import kotlinx.coroutines.launch

class ResponseViewModel(private val api: WebService): ViewModel() {

    val listDemands = mutableStateOf<List<ResponseAPI>>(emptyList())

    val errorMessage = mutableStateOf("")

    fun loadDemands(token: String) {
        viewModelScope.launch {
            try {
                val response = api.getDemand("Bearer $token")
                if (response.isSuccessful) {
                    listDemands.value = response.body() ?: emptyList()
                    errorMessage.value = ""
                } else {
                    errorMessage.value = "Error al cargar las materias: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}