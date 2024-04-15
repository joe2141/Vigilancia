package com.example.vigilancia.network

import ApiService
import android.content.Context
import android.util.Log
import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.R
import com.example.vigilancia.models.LoginResponse
import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.models.VigilanciasResponse
import com.example.vigilancia.models.VigilanteResponse
import com.example.vigilancia.utility.Shared
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager(private val context: Context) {
    private val apiKey: String = context.getString(R.string.api_key)
    private val baseUrl: String = context.getString(R.string.base_url)

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val token = Shared.getToken(context) ?: ""
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .header("api_key", apiKey)
        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    suspend fun login(usuario: String, contrasena: String): LoginResponse? {
        return try {
            service.login(LoginBody(usuario, contrasena))
        } catch (e: HttpException) {
            Log.e("ApiManager", "Error HTTP: ${e.code()}")
            null
        } catch (e: Exception) {
            Log.e("ApiManager", "Error: ${e.localizedMessage}")
            null
        }
    }

    suspend fun getVigilanteDetails(personaid: Int): VigilanteResponse? {
        return try {
            service.getVigilanteidByPersonaId(personaid)
        } catch (e: Exception) {
            Log.e("ApiManager", "Error: ${e.localizedMessage}")
            null
        }
    }
    suspend fun getPreguntas(vigilanciaCategoriaId: Int?, apartado: Int?): PreguntasResponse? {
        return try {
            service.getPreguntas(vigilanciaCategoriaId, apartado)
        } catch (e: Exception) {
            Log.e("ApiManager", "Error al obtener preguntas: ${e.localizedMessage}")
            null
        }
    }

    suspend fun getVigilanciasByVigilanteId(vigilanteId: Int): VigilanciasResponse? {
        return try {
            service.getVigilanciasByVigilanteId(vigilanteId)
        } catch (e: Exception) {
            Log.e("ApiManager", "Error al obtener vigilancias: ${e.localizedMessage}")
            null
        }
    }
}



