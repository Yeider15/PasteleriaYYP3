package com.example.pasteleriayy.model


data class Usuario(
    val id: Long? = null,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String? = null
)
