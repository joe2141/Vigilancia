package com.example.vigilancia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vigilancia.adapter.VigilanciaAdapter

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecyclerView()
    }



    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVigilancias)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VigilanciaAdapter(VigilanceProvider.vigilanceList)

        println(VigilanceProvider.vigilanceList)
    }
}
