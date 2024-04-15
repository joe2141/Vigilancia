package com.example.vigilancia.models

data class VigilanciaDetalle(
    val id: Int,
    val vigilanteId: Int,
    val vigilanciaId: Int,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?,
    val vigilancia: Vigilancia
)