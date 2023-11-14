package com.example.vigilancia.Activity

import ApiService
import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.R
import com.example.vigilancia.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupActionBar()

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
        try {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://siiges-services.2.ie-1.fl0.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.login(LoginBody(usuario, contrasena)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Iniciar HomeActivity cuando el inicio de sesión es exitoso
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()  // Opcional: finalizar LoginActivity para que no se vuelva a ella al presionar 'Atrás'
                } else {
                    // Manejar respuesta no exitosa (mostrar mensaje de error, etc.)
                    Toast.makeText(this@LoginActivity, "Inicio de sesión fallido: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Aquí manejas el fallo en la solicitud, como un error de conexión
                Toast.makeText(this@LoginActivity, "Error al conectar: ${t.message}. Por favor, verifica tu conexión.", Toast.LENGTH_LONG).show()
            }
        })
        } catch (e: Exception) {
            // Aquí manejas el error, por ejemplo, mostrando un Toast
            runOnUiThread {
                Toast.makeText(this, "Error al conectar. Por favor, verifica tu conexión.", Toast.LENGTH_LONG).show()
            }
        }
    }
    }



