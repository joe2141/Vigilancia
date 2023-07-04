package com.example.vigilancia.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.VigilanciasProgramadas


class VigilanceViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val nombre = view.findViewById<TextView>(R.id.tvNombrePlantel)
    val direccion = view.findViewById<TextView>(R.id.tvDirecion)
    val clave = view.findViewById<TextView>(R.id.tvClave)
    val photo = view.findViewById<ImageView>(R.id.ivPlantel)

    fun render(vigilanceModal: VigilanciasProgramadas) {
        nombre.text = vigilanceModal.institucion
        direccion.text = vigilanceModal.direccion
        clave.text = vigilanceModal.clave


    }
}