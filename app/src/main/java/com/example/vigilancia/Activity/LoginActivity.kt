package com.example.vigilancia.Activity

import ApiService
import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .header("api_key", "zaCELgL.0imfnc8mVLWwsAawjYr4Rx-Af50DDqtlx")
                        .method(original.method(), original.body())
                        .build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://siiges-services.2.ie-1.fl0.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

        val service = retrofit.create(ApiService::class.java)

            service.login(LoginBody(usuario, contrasena)).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Log.d("LoginActivity", "Inicio de sesión exitoso")
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val errorMessage = "Inicio de sesión fallido: ${response.errorBody()?.string()}"
                        Log.e("LoginActivity", errorMessage)
                        Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    val errorConnectionMessage = "Error al conectar: ${t.message}. Por favor, verifica tu conexión."
                    Log.e("LoginActivity", errorConnectionMessage, t)
                    Toast.makeText(this@LoginActivity, errorConnectionMessage, Toast.LENGTH_LONG).show()
                }
            })
        } catch (e: Exception) {
            val exceptionMessage = "Excepción al conectar: ${e.message}"
            Log.e("LoginActivity", exceptionMessage, e)
            runOnUiThread {
                Toast.makeText(this, exceptionMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}



