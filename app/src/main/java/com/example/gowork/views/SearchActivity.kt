package com.example.gowork.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gowork.R
import com.example.gowork.views.adapters.BooksAdapter
import com.example.gowork.views.network.GoogleBooksService

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val genre = intent.getStringExtra("GENRE") ?: ""
        val recyclerView: RecyclerView = findViewById(R.id.rv_books)
        recyclerView.layoutManager = LinearLayoutManager(this)

        GoogleBooksService.getBooksByGenre(genre) { books ->
            recyclerView.adapter = BooksAdapter(books)
        }
    }
}