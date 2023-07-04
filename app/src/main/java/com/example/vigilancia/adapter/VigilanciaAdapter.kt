package com.example.vigilancia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.R
import com.example.vigilancia.VigilanciasProgramadas

class VigilanciaAdapter(private val vigilanceList: List<VigilanciasProgramadas>) : RecyclerView.Adapter<VigilanceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VigilanceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VigilanceViewHolder(layoutInflater.inflate(R.layout.item_vigilancias, parent, false))
    }

    override fun onBindViewHolder(holder: VigilanceViewHolder, position: Int) {
        val item =vigilanceList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = vigilanceList.size
    }
