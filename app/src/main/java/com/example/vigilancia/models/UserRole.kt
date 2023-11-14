package com.example.vigilancia.models

data class UserRole(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?
)
