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

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvLoginRedirect.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
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
                startActivity(Intent(this, HomeActivity::class.java)) // Redireciona para HomeActivity
                finish()
            } else {
                binding.tvErrorMessage.text = "Credenciais inválidas!"
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
        }
    }
}
