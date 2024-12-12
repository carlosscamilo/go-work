package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gowork.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializar botões da barra inferior de navegação
        val homeButton = findViewById<Button>(R.id.homeButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val profileButton = findViewById<Button>(R.id.profileButton)

        // Configurar ações para os botões
        homeButton.setOnClickListener {
            Toast.makeText(this, "Home clicado!", Toast.LENGTH_SHORT).show()
        }

        searchButton.setOnClickListener {
            Toast.makeText(this, "Buscar clicado!", Toast.LENGTH_SHORT).show()

            // Navegar para a tela de busca
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            Toast.makeText(this, "Perfil clicado!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }
}
