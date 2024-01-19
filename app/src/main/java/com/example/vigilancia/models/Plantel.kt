package com.example.vigilancia.models

data class Plantel(
    val id: Int,
    val institucionId: Int,
    val domicilioId: Int,
    val correo1: String,
    val correo2: String,
    val correo3: String,
    val claveCentroTrabajo: String,
    val telefono1: String,
    val telefono2: String,
    val telefono3: String,
    val paginaWeb: String,
    val redesSociales: String,
    val especificaciones: String,
    val dimensiones: String,
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?,
    val institucion: Institucion?,
    val domicilio: Domicilio?
)
