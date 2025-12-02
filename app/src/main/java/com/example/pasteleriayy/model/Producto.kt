package com.example.pasteleriayy.model

import androidx.annotation.DrawableRes

data class Producto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    @DrawableRes val imagenResId: Int
)