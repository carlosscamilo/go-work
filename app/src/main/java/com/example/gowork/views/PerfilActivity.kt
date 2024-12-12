package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gowork.R

class PerfilActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var exitButton: ImageView
    private lateinit var editarDadosButton: Button
    private lateinit var nomeTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var senhaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Inicializar views
        backButton = findViewById(R.id.imageView6)
        exitButton = findViewById(R.id.imageView5)
        nomeTextView = findViewById(R.id.textView)
        emailTextView = findViewById(R.id.textView2)
        senhaTextView = findViewById(R.id.textView3)

        // Definindo os valores do perfil (Exemplificando com valores fixos)
        nomeTextView.text = "Paul"
        emailTextView.text = "Email: Pwalker@gmail.com"
        senhaTextView.text = "Senha: ******"

        // Botão de voltar
        backButton.setOnClickListener {
            finish() // Fecha a atividade atual
        }

        // Botão de sair
        exitButton.setOnClickListener {
            finishAffinity() // Encerra todas as atividades do app
        }
    }
}