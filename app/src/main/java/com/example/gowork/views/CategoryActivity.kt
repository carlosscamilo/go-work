package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gowork.R
import com.example.gowork.views.adapters.GenreAdapter

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val genres = listOf("Romance", "Ficção Científica", "Fantasia", "Terror", "Distopia")
        val recyclerView: RecyclerView = findViewById(R.id.rv_categories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = GenreAdapter(genres) { genre ->
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("GENRE", genre)
            startActivity(intent)
        }
    }
}