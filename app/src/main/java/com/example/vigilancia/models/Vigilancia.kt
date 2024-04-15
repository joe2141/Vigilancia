package com.example.vigilancia.models

data class Vigilancia(
    val id: Int,
    val programaId: Int,
    val estatusVigilanciaId: Int,
    val fecha: String,
    val fechaAsignada: String,
    val folio: String,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?,
    val programa: Programa
)
