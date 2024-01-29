package com.example.vigilancia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.models.Pregunta

class PreguntasAdapterInco (private val preguntas: List<Pregunta>) : RecyclerView.Adapter<PreguntasAdapterInco.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPregunta: TextView = view.findViewById(R.id.tv_pregunta)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        val editMultiline: EditText = view.findViewById(R.id.edit_multiline)
        val btnCapturePhoto: Button = view.findViewById(R.id.btn_capture_photo)
        val btnViewPhoto: Button = view.findViewById(R.id.btn_view_photo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pregunta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pregunta = preguntas[position]
        Log.d("PreguntasAdapter", "Mostrando pregunta: ${pregunta.pregunta}")

        // Comenta el código que configura las vistas si solo quieres loguear las preguntas por ahora
        /*
        when(pregunta.vigilanciaTipoPreguntaId) {
            1 -> {
                holder.radioGroup.visibility = View.VISIBLE
                holder.editMultiline.visibility = View.GONE
                holder.btnCapturePhoto.visibility = View.GONE
                holder.btnViewPhoto.visibility = View.GONE

            }
            2 -> {
                holder.radioGroup.visibility = View.GONE
                holder.editMultiline.visibility = View.VISIBLE

            }
            else -> {
                holder.radioGroup.visibility = View.GONE
                holder.editMultiline.visibility = View.GONE
                holder.btnCapturePhoto.visibility = View.GONE
                holder.btnViewPhoto.visibility = View.GONE
            }
        }
        */
    }





    override fun getItemCount() = preguntas.size
}
