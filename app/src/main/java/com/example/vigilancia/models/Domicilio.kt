package com.example.vigilancia.models

data class Domicilio(
    val id: Int,
    val municipioId: Int,
    val estadoId: Int,
    val calle: String,
    val numeroExterior: String,
    val numeroInterior: String,
    val colonia: String,
    val codigoPostal: Int,
    val latitud: String,
    val longitud: String,
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?
)
