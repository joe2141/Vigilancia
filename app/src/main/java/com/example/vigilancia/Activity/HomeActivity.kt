package com.example.vigilancia.Activity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vigilancia.adapter.VigilanciaAdapter
import com.example.vigilancia.databinding.ActivityHomeBinding
import android.content.Intent
import com.example.vigilancia.AreasActivity
import com.example.vigilancia.Providers.VigilanceProvider
import com.example.vigilancia.Providers.VigilanciasProgramadas


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        initRecyclerView()

    }

    private fun initRecyclerView(){

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerVigilancias.layoutManager = manager
        binding.recyclerVigilancias.adapter =
            VigilanciaAdapter(VigilanceProvider.vigilanceList) { vigilance ->
            onItemSelected(
                vigilance
            )
        }
        binding.recyclerVigilancias.addItemDecoration(decoration)
    }

    fun onItemSelected(vigilance: VigilanciasProgramadas) {
        val intent = Intent(this, AreasActivity::class.java)
        // Puedes pasar datos adicionales a la actividad utilizando putExtra si es necesario
        intent.putExtra("direccion", vigilance.direccion)
        intent.putExtra("institucion", vigilance.institucion)
        intent.putExtra("clave", vigilance.clave)
        // Inicia la actividad OtraActivity
        startActivity(intent)
    }
}
