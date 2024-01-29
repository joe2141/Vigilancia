package com.example.vigilancia.Activity
import BaseActivity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Toolbar
import com.example.vigilancia.R
import com.example.vigilancia.fragmets.PreguntasFragmentoInco
import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.network.ApiManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            fetchPreguntas()
    }
    private fun fetchPreguntas() {
        val apiManager = ApiManager(this)
        apiManager.getPreguntas().enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful) {
                    // Ahora accedes a la lista de preguntas a través de response.body()?.data
                    val preguntas = response.body()?.data
                    if (preguntas != null) {
                        Log.d("IncoActivity", "Preguntas: $preguntas")
                        // Aquí puedes hacer lo que necesites con la lista de preguntas
                    }
                } else {
                    Log.e("IncoActivity", "Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call<PreguntasResponse>, t: Throwable) {
                Log.e("IncoActivity", "Error en la llamada al API", t)
            }
        })
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
