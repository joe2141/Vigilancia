package com.example.vigilancia.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.vigilancia.R

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aquí estableces el layout para la actividad final. Debes crear un archivo XML para esto.
        setContentView(R.layout.actividad_final)

    val btnTomarFoto: Button = findViewById(R.id.btnTomarFoto)
    val btnVerFotos: Button = findViewById(R.id.btnVerFotos)
    val btnTerminarVigilancia: Button = findViewById(R.id.btnTerminarVigilancia)

    btnTomarFoto.setOnClickListener {
        // Inicia la actividad para tomar una foto
    }

    btnVerFotos.setOnClickListener {
        // Muestra las fotos que se han tomado
    }

    btnTerminarVigilancia.setOnClickListener {
        // Finaliza la vigilancia y posiblemente regresa al usuario al inicio
        finish()
    }
}
}