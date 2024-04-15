package com.example.vigilancia.models

data class Institucion(
    val id: Int,
    val tipoInstitucionId: Int,
    val nombre: String,
    val razonSocial: String,
    val claveIes: String,
    val historia: String,
    val vision: String,
    val mision: String,
    val valoresInstitucionales: String,
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?
)
