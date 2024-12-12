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

        // Redirecionar para tela de cadastro
        binding.tvLoginRedirect.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        binding.tvErrorMessage.visibility = View.GONE

        if (validateFields(email, password)) {
            authenticateUser(email, password)
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = "O campo email não pode estar vazio"
            binding.textInputLayoutEmail.errorIconDrawable = null
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Formato de e-mail inválido"
            binding.textInputLayoutEmail.errorIconDrawable = null
            isValid = false
        } else {
            binding.textInputLayoutEmail.error = null
        }

        if (password.isEmpty()) {
            binding.textInputLayoutPassword.error = "O campo senha não pode estar vazio"
            binding.textInputLayoutPassword.errorIconDrawable = null
            isValid = false
        } else {
            binding.textInputLayoutPassword.error = null
        }

        return isValid
    }

    private fun authenticateUser(email: String, password: String) {
        binding.btnLogin.isEnabled = false
        binding.btnLogin.text = "Carregando..."

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            binding.btnLogin.isEnabled = true
            binding.btnLogin.text = "Login"
            if (task.isSuccessful) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val errorMessage = "Credenciais inválidas! Verifique o email e a senha e tente novamente."
                binding.tvErrorMessage.text = errorMessage
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
        }
    }
}
