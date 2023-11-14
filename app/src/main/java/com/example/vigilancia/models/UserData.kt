package com.example.vigilancia.models

data class UserData(
    val id: Int,
    val rolId: Int,
    val personaId: Int,
    val usuario: String,
    val correo: String,
    val estatus: Boolean,
    val actualizado: Boolean,
    val tokenNotificaciones: String,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?,
    val rol: UserRole
)