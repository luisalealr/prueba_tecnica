package com.example.prueba_tecnica.data.network

import com.example.prueba_tecnica.data.model.AuthResponse
import com.example.prueba_tecnica.data.model.SigninRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signin")
    suspend fun signin(@Body request: SigninRequest): Response<AuthResponse>
}