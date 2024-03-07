package com.example.vigilancia.activity

import BaseActivity
import Pregunta
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.vigilancia.R
import com.example.vigilancia.fragmets.PreguntasFragmentoInco
import com.example.vigilancia.network.ApiManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class IncoActivity : BaseActivity() {
    private lateinit var apiManager: ApiManager // Define la variable apiManager aquí
    private val categoriaActualId = 1
    private var apartadoActualId = 1
    private val apartados = 4
    private val preguntasCache = hashMapOf<Int, List<Pregunta>>() // Define preguntasCache aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inco)
        setupActionBar()

        apiManager = ApiManager(this)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnAnterior -> {
                    if (apartadoActualId > 1) {
                        cambiarApartado(apartadoActualId - 1)
                    }
                    true
                }
                R.id.btnSiguiente -> {
                    if (apartadoActualId < apartados) {
                        cambiarApartado(apartadoActualId + 1)
                    }
                    true
                }
                else -> false
            }
        }

        cargarPreguntas(apartadoActualId)
    }

    private fun cambiarApartado(nuevoApartado: Int) {
        apartadoActualId = nuevoApartado
        cargarPreguntas(apartadoActualId)
        // Nota: No es necesario modificar la habilitación de los botones aquí ya que usamos BottomNavigationView.
    }

    private fun cargarPreguntas(apartadoId: Int) {
        lifecycleScope.launch {
            try {
                preguntasCache[apartadoId]?.let { preguntas ->
                    actualizarFragmentoConPreguntas(preguntas)
                    return@launch
                }

                val response = apiManager.getPreguntas(categoriaActualId, apartadoId)
                response?.data?.let { preguntas ->
                    preguntasCache[apartadoId] = preguntas
                    actualizarFragmentoConPreguntas(preguntas)
                }
            } catch (e: Exception) {
                Log.e("IncoActivity", "Error al conectar con el servidor", e)
                mostrarError("Error al conectar con el servidor. Por favor, verifica tu conexión a Internet.")
            }
        }
    }

    private fun actualizarFragmentoConPreguntas(preguntas: List<Pregunta>) {
        val fragment = PreguntasFragmentoInco().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("preguntas", ArrayList(preguntas))
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun mostrarError(mensaje: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Error")
            setMessage(mensaje)
            setPositiveButton("Aceptar", null)
        }.show()
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
