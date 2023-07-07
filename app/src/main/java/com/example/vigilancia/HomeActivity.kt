package com.example.vigilancia

import android.app.Activity
import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    fun initRecyclerView(){

        binding.recyclerVigilancias.layoutManager = LinearLayoutManager(this)
        binding.recyclerVigilancias.adapter = VigilanciaAdapter(VigilanceProvider.vigilanceList)
    }
}
