package com.example.vigilancia.Activity

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.vigilancia.R
import com.example.vigilancia.network.ApiManager
import com.example.vigilancia.utility.Shared

class LoginActivity : BaseActivity() {

    private lateinit var apiManager: ApiManager
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar = findViewById(R.id.progressBarLogin)
        setupActionBar()

        apiManager = ApiManager(this)

        val botonEntrar = findViewById<Button>(R.id.loginButton)
        val editTextEmail = findViewById<EditText>(R.id.email)
        val editTextContrasena = findViewById<EditText>(R.id.contraseña)

        botonEntrar.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val contrasena = editTextContrasena.text.toString().trim()
            if (email.isNotEmpty() && contrasena.isNotEmpty()) {
                showProgressBar() // Mostrar el ProgressBar antes de iniciar el login
                login(email, contrasena)
            } else {
                Toast.makeText(this, "Email y contraseña son requeridos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(usuario: String, contrasena: String) {
        apiManager.login(usuario, contrasena) { response ->
            hideProgressBar() // Ocultar el ProgressBar independientemente de la respuesta

            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    if (loginResponse.data.rolId == 16) {
                        Shared.saveToken(this, loginResponse.token)
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            putExtra("personaid", loginResponse.data.personaId)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Error: Usuario no es vigilante", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                val errorMessage = "Inicio de sesión fallido: ${response.errorBody()?.string()}"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
