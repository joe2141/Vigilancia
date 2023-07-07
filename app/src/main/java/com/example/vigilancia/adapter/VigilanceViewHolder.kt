package com.example.vigilancia.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vigilancia.VigilanciasProgramadas
import com.example.vigilancia.databinding.ItemVigilanciasBinding


class VigilanceViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val binding = ItemVigilanciasBinding.bind(view)
    fun render(vigilanceModal: VigilanciasProgramadas) {
        binding.tvNombrePlantel.text = vigilanceModal.institucion
        binding.tvDirecion.text = vigilanceModal.direccion
        binding.tvClave.text = vigilanceModal.clave
        Glide.with(binding.ivPlantel.context).load(vigilanceModal.photo).into(binding.ivPlantel)
    }
}