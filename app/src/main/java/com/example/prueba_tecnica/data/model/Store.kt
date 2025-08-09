package com.example.prueba_tecnica.data.model

data class Store(
    val address: String,
    val email: String,
    val id: Int,
    val keyCode: String,
    val name: String,
    val phone: String,
    val storeTimeZone: StoreTimeZone
)