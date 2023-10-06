package com.example.vigilancia.Activity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Toolbar
import com.example.vigilancia.R
import com.example.vigilancia.fragmets.PreguntasFragmentoInco
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IncoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inco)
        setupActionBar()

        val fabSave: FloatingActionButton = findViewById(R.id.fab_save)


// Establece el listener para el botón flotante
        fabSave.setOnClickListener {
            // Tu acción para el botón de guardar
        }

        val fragment = PreguntasFragmentoInco()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()




    }
}