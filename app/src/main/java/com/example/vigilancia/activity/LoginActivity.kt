package com.example.vigilancia.activity

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
        val editTextUsuario = findViewById<EditText>(R.id.usuario) // Cambio de ID
        val editTextContrasena = findViewById<EditText>(R.id.contraseña)

        botonEntrar.setOnClickListener {
            val usuario = editTextUsuario.text.toString().trim() // Uso de usuario
            val contrasena = editTextContrasena.text.toString().trim()
            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                showProgressBar() // Mostrar el ProgressBar antes de iniciar el login
                login(usuario, contrasena)
            } else {
                Toast.makeText(this, "Usuario y contraseña son requeridos", Toast.LENGTH_LONG).show() // Mensaje actualizado
            }
        }
    }

    private fun login(usuario: String, contrasena: String) {
        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar()
            try {
                val response = apiManager.login(usuario, contrasena)
                if (response != null) {
                    // Manejar la respuesta exitosa aquí.
                    Shared.saveToken(this@LoginActivity, response.token)
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java).apply {
                        putExtra("personaid", response.personaId)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    // Mostrar un mensaje de error.
                    Toast.makeText(this@LoginActivity, "Error en el inicio de sesión", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Error en el inicio de sesión: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            } finally {
                hideProgressBar()
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
