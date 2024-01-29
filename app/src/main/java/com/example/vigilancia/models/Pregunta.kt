package com.example.vigilancia.models

data class Pregunta(
    val id: Int,
    val vigilanciaTipoPreguntaId: Int,
    val vigilanciaApartadoId: Int,
    val vigilanciaCategoriaId: Int,
    val pregunta: String,
    val vigilanciaApartado: VigilanciaApartado,
    val vigilanciaCategoria: VigilanciaCategoria,
    val vigilanciaTipoPregunta: VigilanciaTipoPregunta,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?
)
