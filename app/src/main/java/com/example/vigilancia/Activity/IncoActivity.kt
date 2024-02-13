package com.example.vigilancia.Activity
import BaseActivity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import com.example.vigilancia.R
import com.example.vigilancia.fragmets.PreguntasFragmentoInco
import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.network.ApiManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val categoriaIdParaFiltrar: Int? = 1
        val apartadoParaFiltrar: Int? = 1

        val apiManager = ApiManager(this)
        apiManager.getPreguntas(vigilanciaCategoriaId = categoriaIdParaFiltrar, apartado = apartadoParaFiltrar).enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful) {
                    val preguntasFiltradasPorCategoria = response.body()?.data
                    // Procesa las preguntas filtradas según la categoría
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
