package com.example.prueba_tecnica.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.data.network.WebService
import kotlinx.coroutines.launch

class ResponseViewModel(private val api: WebService): ViewModel() {

    val requestList = mutableStateOf<List<ResponseAPI>>(emptyList())

    val errorMessage = mutableStateOf("")

    val selectedReport = mutableStateOf<ResponseAPI?>(null)

    fun loadReports(token: String) {
        viewModelScope.launch {
            try {
                val response = api.getReport("Bearer $token")
                if (response.isSuccessful) {
                    requestList.value = response.body() ?: emptyList()
                    errorMessage.value = ""
                } else {
                    errorMessage.value = "Error al cargar las peticiones: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }

    fun getReport(id: Int, token: String){
        viewModelScope.launch {
            try {
                val response = api.getReportById(id, "Bearer $token")
                if (response.isSuccessful) {
                    selectedReport.value = response.body()
                    errorMessage.value = ""
                } else {
                    errorMessage.value = "Error al cargar la petición: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}