package com.example.vigilancia.Activity
import BaseActivity
import android.app.AlertDialog
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

        fabSave.setOnClickListener {
        }

        val fragment = PreguntasFragmentoInco()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()


    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Confirmar salida")
            setMessage("¿Estás seguro de que quieres salir? Cualquier cambio no guardado se perderá.")
            setPositiveButton("Salir") { _, _ ->
                super.onBackPressed()
            }
            setNegativeButton("Cancelar", null)
        }.create().show()
    }
}