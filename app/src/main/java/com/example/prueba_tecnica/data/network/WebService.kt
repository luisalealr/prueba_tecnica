package com.example.prueba_tecnica.data.network

import com.example.prueba_tecnica.data.model.ResponseAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface WebService {

    @GET("report-folio/mobile/filters?limit=20&order=DESC")
    suspend fun getReport(@Header("Authorization") authToken: String): Response<List<ResponseAPI>>

    @GET("report-folio/{id}")
    suspend fun getReportById(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<ResponseAPI>
}