package com.example.pasteleriayy.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Promocion(
    val id:String,
    val nombre: String,
    val descripcion: String,
    val fechaFin: LocalDate,
    val precio: Double,
    @DrawableRes val imagenResId: Int
)