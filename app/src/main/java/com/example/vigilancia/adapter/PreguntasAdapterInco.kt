package com.example.vigilancia.adapter

import Pregunta
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

        // Asegúrate de que estás asignando el texto de la pregunta al TextView correctamente
        holder.tvPregunta.text = pregunta.pregunta  // Usa pregunta.pregunta para obtener el texto real de la pregunta

        // Configuración de la visibilidad de los elementos basada en el tipo de pregunta
        when (pregunta.vigilanciaTipoPreguntaId) {
            1 -> {  // SI/NO
                holder.radioGroup.visibility = View.VISIBLE
                holder.editMultiline.visibility = View.GONE
            }
            2 -> {  // Respuesta abierta, numérica, etc.
                holder.radioGroup.visibility = View.GONE
                holder.editMultiline.visibility = View.VISIBLE
            }
            else -> {  // Para cualquier otro tipo de pregunta no manejado específicamente
                holder.radioGroup.visibility = View.GONE
                holder.editMultiline.visibility = View.GONE
            }
        }
        holder.btnCapturePhoto.visibility = View.GONE
        holder.btnViewPhoto.visibility = View.GONE
    }
    override fun getItemCount() = preguntas.size

}
