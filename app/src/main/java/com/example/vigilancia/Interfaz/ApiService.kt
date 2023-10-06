package com.example.vigilancia.Interfaz

import com.example.vigilancia.models.PreguntaResponse
import retrofit2.Call

import retrofit2.http.GET

interface ApiService {
    @GET("/Preguntas")
    fun getPreguntas(): Call<PreguntaResponse>

}