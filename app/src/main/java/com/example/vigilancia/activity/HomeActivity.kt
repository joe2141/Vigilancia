package com.example.vigilancia.activity

import BaseActivity
import android.content.Intent
import com.example.vigilancia.utility.Shared
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.AreasActivity
import com.example.vigilancia.Interfaz.OnVigilanciaClickListener
import com.example.vigilancia.R
import com.example.vigilancia.adapter.VigilanciasAdapter
import com.example.vigilancia.models.VigilanciaDetalle
import com.example.vigilancia.network.ApiManager
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity(), OnVigilanciaClickListener {

    private var personaid: Int = -1
    private lateinit var apiManager: ApiManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerVigilancias)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VigilanciasAdapter(emptyList(), this)

        apiManager = ApiManager(this)
        personaid = intent.getIntExtra("personaid", -1)
        if (personaid != -1) {
            getVigilanteDetails(personaid)
        }
        getVigilanciasByVigilanteId()
    }

    private fun getVigilanteDetails(personaid: Int) {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            val response = apiManager.getVigilanteDetails(personaid)
            progressBar.visibility = View.GONE

            if (response != null) {
                val vigilanciaData = response.data
                Log.d("HomeActivity", "VigilanteID obtenido: ${vigilanciaData.id}") // Corregido
                Shared.saveVigilanteId(this@HomeActivity, vigilanciaData.id) // Corregido
            } else {
                Log.e("HomeActivity", "Error al obtener datos")
            }
        }
    }

    override fun onVigilanciaClick(vigilanciaDetalle: VigilanciaDetalle) {
        // Guardamos el vigilanciaId en las preferencias compartidas
        Shared.saveVigilanciaId(this, vigilanciaDetalle.id)

        // Aquí manejas el clic en un ítem de Vigilancia
        val intent = Intent(this, AreasActivity::class.java)
        // Puedes poner extras en el intent si necesitas pasar información a AreasActivity
        intent.putExtra("vigilanciaId", vigilanciaDetalle.id) // Pasamos el vigilanciaId a la siguiente actividad
        startActivity(intent)
    }

    private fun getVigilanciasByVigilanteId() {
        val vigilanteId = Shared.getVigilanteId(this)
        if (vigilanteId != -1) {
            lifecycleScope.launch {
                try {
                    val response = apiManager.getVigilanciasByVigilanteId(vigilanteId)
                    if (response != null) {
                        Log.d("HomeActivity", "Respuesta obtenida: $response")
                        (recyclerView.adapter as? VigilanciasAdapter)?.updateData(response.data) // Corregido
                    } else {
                        Log.d("HomeActivity", "Respuesta nula recibida del servidor")
                    }
                } catch (e: Exception) {
                    Log.e("HomeActivity", "Error al obtener vigilancias: ${e.message}")
                }
            }
            }
    }
}


