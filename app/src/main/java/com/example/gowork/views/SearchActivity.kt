package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gowork.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)

        // Configuração do campo de busca
        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        // Configuração do RecyclerView de categorias
        val categoriesRecyclerView = findViewById<RecyclerView>(R.id.categoriesRecyclerView)

        // Configuração do RecyclerView de resultados de busca
        val searchResultsRecyclerView = findViewById<RecyclerView>(R.id.searchResultsRecyclerView)


        // Configuração dos botões da barra inferior
        val homeButton = findViewById<Button>(R.id.homeButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val profileButton = findViewById<Button>(R.id.profileButton)

        // Ação ao clicar nos botões
        homeButton.setOnClickListener {
            Toast.makeText(this, "Home selecionado", Toast.LENGTH_SHORT).show()

            // Navegar para a tela HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        searchButton.setOnClickListener {
            Toast.makeText(this, "Você já está na tela de busca!", Toast.LENGTH_SHORT).show()
        }

        profileButton.setOnClickListener {
            Toast.makeText(this, "Perfil selecionado", Toast.LENGTH_SHORT).show()

        }

        // Configuração da ação de busca (exemplo básico)
        searchEditText.setOnEditorActionListener { _, _, _ ->
            val query = searchEditText.text.toString()
            if (query.isNotBlank()) {
                Toast.makeText(this, "Buscando: $query", Toast.LENGTH_SHORT).show()

            }
            true
        }
    }
}
