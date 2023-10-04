package com.example.vigilancia.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.Providers.VigilanciasProgramadas
import com.example.vigilancia.databinding.ItemVigilanciasBinding


class VigilanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemVigilanciasBinding.bind(view)
    fun render(vigilanceModal: VigilanciasProgramadas, onClickListener: (VigilanciasProgramadas) -> Unit) {
        binding.tvNombrePlantel.text = vigilanceModal.institucion
        binding.tvDirecion.text = vigilanceModal.direccion
        binding.tvClave.text = vigilanceModal.clave
        itemView.setOnClickListener { onClickListener(vigilanceModal)}
    }
}