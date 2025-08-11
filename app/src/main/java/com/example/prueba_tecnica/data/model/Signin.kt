package com.example.prueba_tecnica.data.model

data class SigninRequest (
    val username: String,
    val password: String
)

data class AuthResponse(
    val token: String
)
