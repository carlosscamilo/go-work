package com.example.gowork.views

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gowork.R // Importar o R do seu projeto

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Certifique-se de usar o nome correto do arquivo XML

        // Inicializar botões da barra inferior de navegação
        val homeButton = findViewById<Button>(R.id.homeButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val profileButton = findViewById<Button>(R.id.profileButton)

        // Configurar ações para os botões
        homeButton.setOnClickListener {
            Toast.makeText(
                this@HomeActivity,
                "Home clicado!",
                Toast.LENGTH_SHORT
            ).show()
        }

        searchButton.setOnClickListener {
            Toast.makeText(
                this@HomeActivity,
                "Buscar clicado!",
                Toast.LENGTH_SHORT
            ).show()
        }

        profileButton.setOnClickListener {
            Toast.makeText(
                this@HomeActivity,
                "Perfil clicado!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
