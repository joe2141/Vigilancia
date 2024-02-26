package com.example.vigilancia.Activity
import BaseActivity
import Pregunta
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import com.example.vigilancia.R
import com.example.vigilancia.fragmets.PreguntasFragmentoInco
import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.network.ApiManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncoActivity : BaseActivity() {

    private val categoriaActualId = 1 // El ID de la categoría actual, si es estático
    private var apartadoActualId = 1 // Inicializa con el primer ID de apartado
    private var maxApartadoId = 1 // Se ajustará dinámicamente


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inco)
        setupActionBar()
        inicializarMaxApartadoId()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.arrow_left -> {
                    if (apartadoActualId > 1) {
                        apartadoActualId--
                        cargarPreguntas(apartadoActualId)
                    }
                    true
                }
                R.id.arrow_right -> {
                    if (apartadoActualId < maxApartadoId) {
                        apartadoActualId++
                        cargarPreguntas(apartadoActualId)
                    }
                    true
                }
                else -> false
            }
        }


        cargarPreguntas(apartadoActualId)
    }

    private fun inicializarMaxApartadoId() {
        val apiManager = ApiManager(this)
        apiManager.getPreguntas(vigilanciaCategoriaId = categoriaActualId, apartado = null).enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful) {
                    val preguntas = response.body()?.data ?: emptyList()
                    maxApartadoId = preguntas.maxOf { it.vigilanciaApartadoId }
                    Log.d("IncoActivity", "MaxApartadoId actualizado a: $maxApartadoId")
                    // Ahora puedes cargar preguntas para el primer apartado o configurar la UI según este nuevo valor
                } else {
                    Log.e("IncoActivity", "Error al cargar todas las preguntas: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PreguntasResponse>, t: Throwable) {
                Log.e("IncoActivity", "Error al conectar con el servidor para inicializar maxApartadoId", t)
            }
        })
    }

    private val preguntasCache = hashMapOf<Int, List<Pregunta>>()


    private fun cargarPreguntas(apartadoId: Int) {
        Log.d("IncoActivity", "Cargando preguntas para el apartado: $apartadoId")

        preguntasCache[apartadoId]?.let {
            Log.d("IncoActivity", "Usando cache para el apartado: $apartadoId")
            actualizarFragmentoConPreguntas(it)
            return
        }

        val apiManager = ApiManager(this)
        apiManager.getPreguntas(vigilanciaCategoriaId = categoriaActualId, apartado = apartadoId).enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful) {
                    val preguntas = response.body()?.data ?: emptyList()
                    preguntasCache[apartadoId] = preguntas

                    // Actualiza el valor máximo de apartadoId
                    preguntas.forEach {
                        if (it.vigilanciaApartadoId > maxApartadoId) {
                            maxApartadoId = it.vigilanciaApartadoId
                        }
                    }

                    Log.d("IncoActivity", "Preguntas cargadas para el apartado: $apartadoId")
                    actualizarFragmentoConPreguntas(preguntas)
                } else {
                    Log.e("IncoActivity", "Error cargando las preguntas. Código de error: ${response.code()}")
                    mostrarError("Error cargando las preguntas. Código de error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PreguntasResponse>, t: Throwable) {
                Log.e("IncoActivity", "Error al conectar con el servidor", t)
                mostrarError("Error al conectar con el servidor. Por favor, verifica tu conexión a Internet.")
            }
        })
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
