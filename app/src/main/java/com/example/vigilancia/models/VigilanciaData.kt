package com.example.vigilancia.models

data class VigilanciaData(
    val id: Int,
    val personaId: Int,
    val persona: Persona,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?
)
