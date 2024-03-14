package com.example.vigilancia
import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.vigilancia.activity.IncoActivity


class AreasActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        setupActionBar()

        // Recibe el vigilanciaId del Intent
        val vigilanciaId = intent.getIntExtra("vigilanciaId", -1) // -1 como valor por defecto en caso de no encontrar el extra
        if (vigilanciaId != -1) {
            Log.d("AreasActivity", "Vigilancia ID: $vigilanciaId")
            // Aquí puedes usar el vigilanciaId como necesites
        } else {
            Log.e("AreasActivity", "No se encontró Vigilancia ID en el Intent")
        }

        val buttonIncorporacion: Button = findViewById(R.id.bt_Incorporacion)
        buttonIncorporacion.setOnClickListener {
            val intent = Intent(this, IncoActivity::class.java)
            // Si deseas pasar el vigilanciaId a IncoActivity, puedes agregarlo al Intent aquí
            intent.putExtra("vigilanciaId", vigilanciaId)
            startActivity(intent)
        }
    }
}