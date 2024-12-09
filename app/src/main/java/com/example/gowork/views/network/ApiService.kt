package com.example.gowork.network

import com.example.gowork.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("category") category: String? = null
    ): Response<ApiResponse>
}
