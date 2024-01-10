package com.example.vigilancia.Activity

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vigilancia.R
import com.example.vigilancia.network.ApiManager
import com.example.vigilancia.utility.Shared


class LoginActivity : BaseActivity() {

    private lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupActionBar()

        apiManager = ApiManager(this)

        val botonEntrar = findViewById<Button>(R.id.loginButton)
        val editTextEmail = findViewById<EditText>(R.id.email)
        val editTextContrasena = findViewById<EditText>(R.id.contraseña)

        botonEntrar.setOnClickListener {
            val email = editTextEmail.text.toString()
            val contrasena = editTextContrasena.text.toString()
            login(email, contrasena)
        }
    }
    private fun login(usuario: String, contrasena: String) {
        apiManager.login(usuario, contrasena) { response ->
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    if (loginResponse.data.rolId == 16) {
                        Shared.saveToken(this, loginResponse.token)
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("personaid", loginResponse.data.personaId)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Error: Usuario no es vigilante", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                val errorMessage = "Inicio de sesión fallido: ${response.errorBody()?.string()}"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}
