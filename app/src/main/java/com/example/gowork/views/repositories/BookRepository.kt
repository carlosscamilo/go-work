package com.example.gowork.data

import com.example.gowork.network.RetrofitClient
import com.example.gowork.models.ApiResponse
import com.example.gowork.models.Book
import com.example.gowork.models.toBook
import retrofit2.Response

class BookRepository {

    suspend fun searchBooks(query: String, category: String? = null): List<Book> {
        val response: Response<ApiResponse> = RetrofitClient.api.searchBooks(query, category)

        if (response.isSuccessful) {
            return response.body()?.docs?.map { it.toBook() } ?: emptyList()
        } else {
            throw Exception("Erro ao carregar os livros.")
        }
    }
}
