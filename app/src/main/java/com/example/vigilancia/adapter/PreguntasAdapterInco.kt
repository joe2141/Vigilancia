package com.example.vigilancia.adapter

import Pregunta
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R

class PreguntasAdapterInco(private val context: Context, private val preguntas: List<Pregunta>) : RecyclerView.Adapter<PreguntasAdapterInco.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPregunta: TextView = view.findViewById(R.id.tv_pregunta)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        val editMultiline: EditText = view.findViewById(R.id.edit_multiline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pregunta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pregunta = preguntas[position]
        holder.tvPregunta.text = pregunta.pregunta

        // Limpiar el listener anterior para evitar llamadas redundantes
        holder.radioGroup.setOnCheckedChangeListener(null)

        val respuestaGuardada = obtenerRespuesta(pregunta.id)
        when (pregunta.vigilanciaTipoPreguntaId) {
            1 -> { // SI/NO/N/A
                holder.radioGroup.visibility = View.VISIBLE
                holder.editMultiline.visibility = View.GONE

                val radioButtonId = when (respuestaGuardada) {
                    "Sí" -> R.id.rb_si
                    "No" -> R.id.rb_no
                    "N/A" -> R.id.rb_na
                    else -> -1
                }
                if (radioButtonId != -1) holder.radioGroup.check(radioButtonId)

                holder.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    val respuestaSeleccionada = when (checkedId) {
                        R.id.rb_si -> "Sí"
                        R.id.rb_no -> "No"
                        R.id.rb_na -> "N/A"
                        else -> ""
                    }
                    guardarRespuesta(pregunta.id, respuestaSeleccionada)
                }
            }
            2 -> { // Respuesta abierta
                holder.radioGroup.visibility = View.GONE
                holder.editMultiline.visibility = View.VISIBLE
                holder.editMultiline.setText(respuestaGuardada)
                holder.editMultiline.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        guardarRespuesta(pregunta.id, s.toString())
                    }
                })
            }
        }
    }

    private fun guardarRespuesta(preguntaId: Int, respuesta: String) {
        val sharedPreferences = context.getSharedPreferences("PreguntasRespuestas", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("Respuesta_$preguntaId", respuesta)
            apply()
        }
    }

    private fun obtenerRespuesta(preguntaId: Int): String {
        val sharedPreferences = context.getSharedPreferences("PreguntasRespuestas", Context.MODE_PRIVATE)
        return sharedPreferences.getString("Respuesta_$preguntaId", "") ?: ""
    }

    override fun getItemCount() = preguntas.size
}