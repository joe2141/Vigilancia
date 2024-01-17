package com.example.vigilancia.network

import ApiService
import android.content.Context
import android.util.Log
import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.R
import com.example.vigilancia.models.LoginResponse
import com.example.vigilancia.models.VigilanciasResponse
import com.example.vigilancia.models.VigilanteResponse
import com.example.vigilancia.utility.Shared
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager(context: Context) {
    private val apiKey: String = context.getString(R.string.api_key)
    private val baseUrl: String = context.getString(R.string.base_url)
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            // Obtener el token de las preferencias compartidas
            val token = Shared.getToken(context)

            val request = original.newBuilder()
                .header("api_key", apiKey)
                .apply {
                    // Añadir el token Bearer si está disponible
                    token?.let {
                        header("Authorization", "Bearer $it")
                    }
                }
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    private val service = retrofit.create(ApiService::class.java)


    fun login(usuario: String, contrasena: String, callback: (Response<LoginResponse>) -> Unit) {
        service.login(LoginBody(usuario, contrasena)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback(response)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ApiManager", "Error en la llamada API", t)
            }
        })
    }
    fun getVigilanteDetails(personaid: Int, callback: (Response<VigilanteResponse>) -> Unit) {
        service.getVigilanteidByPersonaId(personaid).enqueue(object : Callback<VigilanteResponse> {
            override fun onResponse(call: Call<VigilanteResponse>, response: Response<VigilanteResponse>) {
                callback(response)
            }

            override fun onFailure(call: Call<VigilanteResponse>, t: Throwable) {
                Log.e("ApiManager", "Error en la llamada API", t)
            }
        })
    }

    fun getVigilanciasByVigilanteId(vigilanteId: Int, callback: (Response<VigilanciasResponse>) -> Unit) {
        service.getVigilanciasByVigilanteId(vigilanteId).enqueue(object : Callback<VigilanciasResponse> {
            override fun onResponse(call: Call<VigilanciasResponse>, response: Response<VigilanciasResponse>) {
                callback(response)
            }

            override fun onFailure(call: Call<VigilanciasResponse>, t: Throwable) {
                Log.e("ApiManager", "Error en la llamada API", t)
            }
        })
    }
}


