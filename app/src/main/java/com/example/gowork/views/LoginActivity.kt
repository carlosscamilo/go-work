package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gowork.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout com View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Configurar clique no botão de login
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvLoginRedirect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun loginUser() {
        // Obter valores dos campos de entrada
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Limpar mensagem de erro
        binding.tvErrorMessage.visibility = View.GONE

        if (validateFields(email, password)) {
            authenticateUser(email, password)
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        var fieldsValidated = true

        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = "O campo email não pode estar vazio"
            binding.textInputLayoutEmail.errorIconDrawable = null
            fieldsValidated = false // Define a variável como false se o email estiver vazio
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Formato de e-mail inválido"
            binding.textInputLayoutEmail.errorIconDrawable = null
            fieldsValidated = false // Define a variável como false se o email for inválido
        } else {
            binding.textInputLayoutEmail.error = null
        }

        if (password.isEmpty()) {
            binding.textInputLayoutPassword.error = "O campo senha não pode estar vazio"
            binding.textInputLayoutPassword.errorIconDrawable = null
            fieldsValidated = false // Define a variável como false se a senha estiver vazia
        } else {
            binding.textInputLayoutPassword.error = null
        }

        return fieldsValidated
    }

    private fun authenticateUser(email: String, password: String) {
        // Mostrar indicador de carregamento (opcional)
        binding.btnLogin.isEnabled = false
        binding.btnLogin.text = "Carregando..."

        // Autenticar com o Firebase
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            // Reativar o botão
            binding.btnLogin.isEnabled = true
            binding.btnLogin.text = "Login"
            if (task.isSuccessful) {
                // Login bem-sucedido
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Falha no login: mostrar mensagem no TextView
                val errorMessage = "Credenciais inválidas! Verifique o email e a senha e tente novamente."
                binding.tvErrorMessage.text = errorMessage
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
        }
    }
}
