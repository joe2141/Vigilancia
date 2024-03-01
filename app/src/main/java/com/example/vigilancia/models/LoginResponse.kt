package com.example.vigilancia.models

data class LoginResponse(
    val token: String,
    val personaId: Int,
    val rolId: Int
)
