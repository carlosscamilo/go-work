package com.example.gowork.views

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gowork.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val nameEditText = binding.etName
        val emailEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val signInButton = binding.btnSignIn
        val loginRedirectText = binding.tvLoginRedirect

        signInButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (!validateFields(name, email, password)) {
                return@setOnClickListener
            }

            authenticateUser(name, email, password)
        }

        loginRedirectText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validateFields(name: String, email: String, password: String): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            binding.textInputLayoutName.error = "O campo nome não pode estar vazio"
            binding.textInputLayoutName.errorIconDrawable = null
            isValid = false
        } else {
            binding.textInputLayoutName.error = null
        }

        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = "O campo email não pode estar vazio"
            binding.textInputLayoutEmail.errorIconDrawable = null
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Email inválido"
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

    private fun authenticateUser(name: String, email: String, password: String) {
        binding.btnSignIn.isEnabled = false
        binding.btnSignIn.text = "Carregando..."
        binding.tvErrorMessage.visibility = View.GONE


        auth.fetchSignInMethodsForEmail(email)
            .addOnSuccessListener { result ->
                if (!result.signInMethods.isNullOrEmpty()) {
                    binding.tvErrorMessage.text = "Já existe uma conta com este email"
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    binding.btnSignIn.isEnabled = true // Reativar botão
                    binding.btnSignIn.text = "Sign In" // Restaurar texto do botão
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            binding.btnSignIn.isEnabled = true
                            binding.btnSignIn.text = "Sign In"
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid
                                val user = hashMapOf(
                                    "name" to name, "email" to email
                                )
                                if (userId != null) {
                                    firestore.collection("users").document(userId).set(user)
                                        .addOnSuccessListener {
                                            startActivity(Intent(this, MainActivity::class.java))
                                            finish()
                                        }.addOnFailureListener {
                                            binding.tvErrorMessage.text =
                                                "Erro ao salvar usuário no Firestore"
                                            binding.tvErrorMessage.visibility = View.VISIBLE
                                        }
                                }
                            } else {
                                binding.tvErrorMessage.text = "O Email informado já está cadastrado no sistema"
                                binding.tvErrorMessage.visibility = View.VISIBLE
                            }
                        }
                }
            }.addOnFailureListener { exception ->
                // Tratar erro ao verificar email existente
                binding.tvErrorMessage.text =
                    "Erro ao verificar email existente: ${exception.message}"
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.btnSignIn.isEnabled = true // Reativar botão
                binding.btnSignIn.text = "Sign In" // Restaurar texto do botão
            }
    }
}
