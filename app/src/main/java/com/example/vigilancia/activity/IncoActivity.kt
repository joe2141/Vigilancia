package com.example.vigilancia.activity

import BaseActivity
import Pregunta
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
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
            val comentarios = findViewById<EditText>(R.id.editTextComentarios).text.toString()
            guardarComentarios(apartadoActualId, comentarios)
            when (item.itemId) {
                R.id.btnAnterior -> {
                    if (apartadoActualId > 1) {
                        cambiarApartado(apartadoActualId - 1)
                    }
                    true
                }
                R.id.btnSiguiente -> {
                    val comentariosText = findViewById<EditText>(R.id.editTextComentarios).text.toString()
                    if (apartadoActualId < apartados) {
                        guardarComentarios(apartadoActualId, comentariosText)
                        cambiarApartado(apartadoActualId + 1)
                    }
                    true
                }
                else -> false
            }
        }

        cargarPreguntas(apartadoActualId)
    }

    private fun mostrarComentariosDelApartado(apartadoId: Int) {
        val sharedPreferences = getSharedPreferences("PreferenciasComentarios", Context.MODE_PRIVATE)
        val comentarios = sharedPreferences.getString("comentarios_$apartadoId", "")
        findViewById<EditText>(R.id.editTextComentarios).setText(comentarios)
    }

    private fun cambiarApartado(nuevoApartado: Int) {
        apartadoActualId = nuevoApartado
        mostrarComentariosDelApartado(apartadoActualId)
        cargarPreguntas(apartadoActualId)
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
                putInt("vigilanciaId", intent.getIntExtra("vigilanciaId", -1))
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun guardarComentarios(apartadoId: Int, textoComentarios: String) {
        val sharedPreferences = getSharedPreferences("PreferenciasComentarios", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("comentarios_$apartadoId", textoComentarios)
            apply()
        }
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

    override fun onPause() {
        super.onPause()
        val comentarios = findViewById<EditText>(R.id.editTextComentarios).text.toString()
        guardarComentarios(apartadoActualId, comentarios)
    }
    override fun onResume() {
        super.onResume()
        mostrarComentariosDelApartado(apartadoActualId)
    }

}
