package com.example.vigilancia.Activity

import BaseActivity
import com.example.vigilancia.utility.Shared
import android.os.Bundle
import android.util.Log
import com.example.vigilancia.network.ApiManager

class HomeActivity : BaseActivity() {

    private var personaid: Int = -1
    private lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiManager = ApiManager(this)

        personaid = intent.getIntExtra("personaid", -1)
        if (personaid != -1) {
            getVigilanteDetails(personaid)
        }

        getVigilanciasByVigilanteId()
    }

    private fun getVigilanteDetails(personaid: Int) {
        apiManager.getVigilanteDetails(personaid) { response ->
            if (response.isSuccessful) {
                val vigilanciaData = response.body()?.data
                Log.d("HomeActivity", "VigilanteID obtenido: ${vigilanciaData?.id}")
                Shared.saveVigilanteId(this, vigilanciaData?.id ?: -1)
            } else {
                Log.e("HomeActivity", "Error al obtener datos: ${response.errorBody()?.string()}")
            }
        }
    }

    private fun getVigilanciasByVigilanteId() {
        val vigilanteId = Shared.getVigilanteId(this)
        if (vigilanteId != -1) {
            apiManager.getVigilanciasByVigilanteId(vigilanteId) { response ->
                if (response.isSuccessful) {
                    val vigilancias = response.body()?.data
                    Log.d("HomeActivity", "Vigilancias obtenidas: $vigilancias")
                } else {
                    Log.e("HomeActivity", "Error al obtener vigilancias: ${response.errorBody()?.string()}")
                }
            }
        } else {
            Log.e("HomeActivity", "Vigilante ID no encontrado en las preferencias compartidas")
        }
    }
}


