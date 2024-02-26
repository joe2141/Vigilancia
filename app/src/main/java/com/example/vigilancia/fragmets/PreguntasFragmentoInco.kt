package com.example.vigilancia.fragmets

import Pregunta
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
        // Recupera las preguntas pasadas como argumento al fragmento
        val preguntas = arguments?.getParcelableArrayList<Pregunta>("preguntas")
        preguntas?.let {
            setupRecyclerView(view, it)
        }
    }

    private fun setupRecyclerView(view: View, preguntas: List<Pregunta>) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_preguntas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PreguntasAdapterInco(preguntas)
    }
}
