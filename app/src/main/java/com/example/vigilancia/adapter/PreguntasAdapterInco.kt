package com.example.vigilancia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.models.Pregunta

class PreguntasAdapterInco (private val preguntas: List<Pregunta>) : RecyclerView.Adapter<PreguntasAdapterInco.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPregunta: TextView = view.findViewById(R.id.tv_pregunta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pregunta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pregunta = preguntas[position]
        Log.d("PreguntasAdapter", "Mostrando pregunta: ${pregunta.Pregunta}")
        holder.tvPregunta.text = pregunta.Pregunta
    }

    override fun getItemCount() = preguntas.size
}
