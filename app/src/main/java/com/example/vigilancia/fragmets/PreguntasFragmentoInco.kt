package com.example.vigilancia.fragmets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.adapter.PreguntasAdapterInco
import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.network.ApiManager

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreguntasFragmentoInco : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_preguntas_inco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Encuentra el RecyclerView en el layout
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_preguntas)
        // Configura el LinearLayoutManager para el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Obtiene la instancia de ApiManager para realizar llamadas a la API
        val apiManager = ApiManager(requireContext())

        // Realiza la llamada a la API para obtener las preguntas
        apiManager.getPreguntas().enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful) {
                    // Obtiene la lista de preguntas de la respuesta
                    val preguntas = response.body()?.data ?: emptyList()

                    // Configura el RecyclerView con el adaptador, pasando la lista de preguntas
                    recyclerView.adapter = PreguntasAdapterInco(preguntas)
                } else {
                    Log.e("PreguntasFragment", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PreguntasResponse>, t: Throwable) {
                Log.e("PreguntasFragment", "Error en la petición: ${t.message}")
            }
        })
    }
}
