package com.example.gowork

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var searchBox: EditText
    private lateinit var buttonSearch: ImageButton
    private lateinit var buttonProfile: ImageButton
    private lateinit var homeButton: ImageButton
    private lateinit var buttonRomance: Button
    private lateinit var buttonHorror: Button
    private lateinit var buttonFantasy: Button
    private lateinit var buttonDystopia: Button
    private lateinit var buttonMystery: Button
    private lateinit var buttonFiction: Button
    private lateinit var buttonAnime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBox = findViewById(R.id.search_box)
        buttonSearch = findViewById(R.id.button_search)
        buttonProfile = findViewById(R.id.button_profile)
        homeButton = findViewById(R.id.homeButton)
        buttonRomance = findViewById(R.id.button_romance)
        buttonHorror = findViewById(R.id.button_horror)
        buttonFantasy = findViewById(R.id.button_fantasy)
        buttonDystopia = findViewById(R.id.button_dystopia)
        buttonMystery = findViewById(R.id.button_mystery)
        buttonFiction = findViewById(R.id.button_fiction)
        buttonAnime = findViewById(R.id.button_anime)

        buttonSearch.setOnClickListener { performSearch() }
        buttonProfile.setOnClickListener { openProfile() }
        homeButton.setOnClickListener { goToHome() }

        buttonRomance.setOnClickListener { showCategoryToast("Romance") }
        buttonHorror.setOnClickListener { showCategoryToast("Terror") }
        buttonFantasy.setOnClickListener { showCategoryToast("Fantasia") }
        buttonDystopia.setOnClickListener { showCategoryToast("Distopia") }
        buttonMystery.setOnClickListener { showCategoryToast("Mistério") }
        buttonFiction.setOnClickListener { showCategoryToast("Ficção") }
        buttonAnime.setOnClickListener { showCategoryToast("Anime") }
    }

    private fun performSearch() {
        val query = searchBox.text.toString().trim()
        if (query.isNotEmpty()) {
            Toast.makeText(this, "Pesquisando por: $query", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Por favor, insira um termo de pesquisa.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openProfile() {
        Toast.makeText(this, "Abrindo perfil...", Toast.LENGTH_SHORT).show()
    }

    private fun goToHome() {
        Toast.makeText(this, "Voltando para a Home.", Toast.LENGTH_SHORT).show()
    }

    private fun showCategoryToast(category: String) {
        Toast.makeText(this, "Categoria: $category", Toast.LENGTH_SHORT).show()
    }
}
