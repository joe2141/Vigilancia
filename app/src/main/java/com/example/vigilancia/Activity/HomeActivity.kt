package com.example.vigilancia.Activity

import BaseActivity
import android.content.Intent
import com.example.vigilancia.utility.Shared
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.AreasActivity
import com.example.vigilancia.Interfaz.OnVigilanciaClickListener
import com.example.vigilancia.R
import com.example.vigilancia.adapter.VigilanciasAdapter
import com.example.vigilancia.models.Vigilancia
import com.example.vigilancia.models.VigilanciaDetalle
import com.example.vigilancia.network.ApiManager

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
        progressBar.visibility = View.VISIBLE

        apiManager.getVigilanteDetails(personaid) { response ->
            progressBar.visibility = View.GONE

            if (response.isSuccessful) {
                val vigilanciaData = response.body()?.data
                Log.d("HomeActivity", "VigilanteID obtenido: ${vigilanciaData?.id}")
                Shared.saveVigilanteId(this, vigilanciaData?.id ?: -1)
            } else {
                Log.e("HomeActivity", "Error al obtener datos: ${response.errorBody()?.string()}")
            }
        }
    }
    override fun onVigilanciaClick(vigilanciaDetalle: VigilanciaDetalle) {
        // Aquí manejas el clic en un ítem de Vigilancia
        val intent = Intent(this, AreasActivity::class.java)
        // Puedes poner extras en el intent si necesitas pasar información a AreasActivity
        startActivity(intent)
    }


    private fun getVigilanciasByVigilanteId() {
        val vigilanteId = Shared.getVigilanteId(this)
        if (vigilanteId != -1) {
            apiManager.getVigilanciasByVigilanteId(vigilanteId) { response ->
                if (response.isSuccessful) {
                    val vigilanciasDetalle = response.body()?.data
                    runOnUiThread {
                        (recyclerView.adapter as? VigilanciasAdapter)?.updateData(vigilanciasDetalle ?: emptyList())
                    }
                } else {
                    Log.e("HomeActivity", "Error al obtener vigilancias: ${response.errorBody()?.string()}")
                }
            }
        } else {
            Log.e("HomeActivity", "Vigilante ID no encontrado en las preferencias compartidas")
        }
    }
}


