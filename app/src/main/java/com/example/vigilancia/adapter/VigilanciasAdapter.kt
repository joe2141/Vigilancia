package com.example.vigilancia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.models.Vigilancia
import com.example.vigilancia.models.VigilanciaDetalle

class VigilanciasAdapter(private var vigilancias: List<VigilanciaDetalle>) : RecyclerView.Adapter<VigilanciasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvClave: TextView = view.findViewById(R.id.tvClave)
        val tvNombrePlantel: TextView = view.findViewById(R.id.tvNombrePlantel)
        val tvDirecion: TextView = view.findViewById(R.id.tvDirecion)
        val tvfecha: TextView = view.findViewById(R.id.tvfecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vigilancias, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vigilanciaDetalle = vigilancias[position]
        holder.tvClave.text = vigilanciaDetalle.vigilancia.folio
        holder.tvNombrePlantel.text = vigilanciaDetalle.vigilancia.programa.nombre
        holder.tvDirecion.text = vigilanciaDetalle.vigilancia.programa.plantelId.toString()
        holder.tvfecha.text = vigilanciaDetalle.vigilancia.fechaAsignada



    }
    fun updateData(newVigilancias: List<VigilanciaDetalle>) {
        this.vigilancias = newVigilancias
        notifyDataSetChanged()
    }

    override fun getItemCount() = vigilancias.size
}
