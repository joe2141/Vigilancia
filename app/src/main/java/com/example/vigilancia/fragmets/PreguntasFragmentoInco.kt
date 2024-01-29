package com.example.vigilancia.fragmets

import ApiService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.vigilancia.Interfaz.ApiService
import com.example.vigilancia.R
import com.example.vigilancia.adapter.PreguntasAdapterInco

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreguntasFragmentoInco : Fragment() {

    object ApiClient {
        private const val BASE_URL = "https://demo5296200.mockable.io/"

        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preguntas_inco, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = ApiClient.instance
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_preguntas)
        recyclerView.layoutManager = LinearLayoutManager(context)  // Añade el LayoutManager aquí

        apiService.getPreguntas().enqueue(object: Callback<PreguntaResponse> {
            override fun onResponse(call: Call<PreguntaResponse>, response: Response<PreguntaResponse>) {
                if (response.isSuccessful) {
                    val preguntas = response.body()?.Preguntas ?: emptyList()
                    Log.d("PreguntasFragment", "Preguntas recibidas: $preguntas")
                    recyclerView.adapter = PreguntasAdapterInco(preguntas)
                } else {
                    Log.e("PreguntasFragment", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PreguntaResponse>, t: Throwable) {
                Log.e("PreguntasFragment", "Error en la petición: ${t.message}")
            }
        })
    }*/
}
