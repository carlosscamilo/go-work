package com.example.gowork.views.network

import android.util.Log
import com.example.gowork.views.models.Book
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object GoogleBooksService {
    private val client = OkHttpClient()

    fun getBooksByGenre(genre: String, callback: (List<Book>) -> Unit) {
        val url = "https://www.googleapis.com/books/v1/volumes?q=subject:$genre"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("GoogleBooksService", "Error fetching books: ${e.message}")
                callback(emptyList())
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("GoogleBooksService", "Unsuccessful response")
                    callback(emptyList())
                    return
                }

                val responseBody = response.body?.string() ?: ""
                try {
                    val books = parseBooksFromJson(responseBody)
                    callback(books)
                } catch (e: Exception) {
                    Log.e("GoogleBooksService", "Error parsing JSON: ${e.message}")
                    callback(emptyList())
                }
            }
        })
    }

    private fun parseBooksFromJson(jsonResponse: String): List<Book> {
        val books = mutableListOf<Book>()
        val jsonObject = JSONObject(jsonResponse)
        val items = jsonObject.optJSONArray("items") ?: return books

        for (i in 0 until items.length()) {
            val item = items.getJSONObject(i)
            val volumeInfo = item.getJSONObject("volumeInfo")
            books.add(
                Book(
                    title = volumeInfo.getString("title"),
                    authors = volumeInfo.optJSONArray("authors")?.join(", ") ?: "",
                    description = volumeInfo.optString("description", "No description available")
                )
            )
        }
        return books
    }
}
