package com.example.vigilancia.Activity

import BaseActivity
import com.example.vigilancia.adapter.VigilanciaAdapter
import com.example.vigilancia.databinding.ActivityHomeBinding
import com.example.vigilancia.AreasActivity
import com.example.vigilancia.Providers.VigilanceProvider
import com.example.vigilancia.Providers.VigilanciasProgramadas
import com.example.vigilancia.utility.Shared
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import android.util.Log
import com.example.vigilancia.models.VigilanciaResponse
import com.example.vigilancia.network.ApiManager
import retrofit2.Response

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var personaid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        initRecyclerView()

        personaid = intent.getIntExtra("personaid", -1)
        if (personaid != -1) {
            val apiManager = ApiManager(this)
            apiManager.getVigilanciaDetails(personaid) { response ->
                if (response.isSuccessful) {
                    val vigilanciaData = response.body()?.data
                    Log.d("HomeActivity", "VigilanteID obtenido: ${vigilanciaData?.id}")
                    Shared.saveVigilanteId(this, vigilanciaData?.id ?: -1)
                } else {
                    Log.e("HomeActivity", "Error al obtener datos: ${response.errorBody()?.string()}")
                }
            }
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerVigilancias.layoutManager = manager
        binding.recyclerVigilancias.adapter =
            VigilanciaAdapter(VigilanceProvider.vigilanceList) { vigilance ->
                onItemSelected(vigilance)
            }
        binding.recyclerVigilancias.addItemDecoration(decoration)
    }

    private fun onItemSelected(vigilance: VigilanciasProgramadas) {
        val intent = Intent(this, AreasActivity::class.java).apply {
            putExtra("direccion", vigilance.direccion)
            putExtra("institucion", vigilance.institucion)
            putExtra("clave", vigilance.clave)
        }
        startActivity(intent)
    }
}
