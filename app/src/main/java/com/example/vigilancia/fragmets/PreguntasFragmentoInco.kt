package com.example.vigilancia.fragmets

import Pregunta
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.adapter.PreguntasAdapterInco

class PreguntasFragmentoInco : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_preguntas_inco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preguntas = arguments?.getParcelableArrayList<Pregunta>("preguntas")
        val vigilanciaId = arguments?.getInt("vigilanciaId", -1) ?: -1
        preguntas?.let {
            setupRecyclerView(view, it, vigilanciaId)
        }
    }

    private fun setupRecyclerView(view: View, preguntas: List<Pregunta>, vigilanciaId: Int) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_preguntas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = context?.let { PreguntasAdapterInco(it, preguntas, vigilanciaId) }
    }
}