package com.example.vigilancia

import android.app.Activity
import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.adapter.VigilanciaAdapter
import com.example.vigilancia.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    fun  onItemSelected(vigilance: VigilanciasProgramadas){
        Toast.makeText(this, vigilance.direccion, Toast.LENGTH_SHORT).show()
    }
}
