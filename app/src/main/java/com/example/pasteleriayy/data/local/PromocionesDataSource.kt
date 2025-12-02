package com.example.pasteleriayy.data.local

import com.example.pasteleriayy.R
import com.example.pasteleriayy.model.Promocion
import java.time.LocalDate

object PromocionesDataSource {

    fun getListaPromociones(): List<Promocion> = listOf(
        Promocion(
            id = "OFR-001",
            nombre = "Dúo de Chocolate Loco 🍫🍫",
            descripcion = "¡Lleva 2 Tortas Cuadradas de Chocolate por un precio único! Perfecto para compartir (o no).",
            fechaFin = LocalDate.of(2025, 12, 15),
            precio = 75000.0,
            imagenResId = R.drawable.pr001
        ),
        Promocion(
            id = "OFR-002",
            nombre = "Frescura de Verano 🍓",
            descripcion = "Torta Cuadrada de Frutas con un 15% de descuento para celebrar la temporada.",
            fechaFin = LocalDate.of(2025, 12, 31),
            precio = 42500.0,
            imagenResId = R.drawable.pr002
        ),
        Promocion(
            id = "OFR-003",
            nombre = "Café y Vainilla ☕",
            descripcion = "Al comprar la Torta Circular de Vainilla , te regalamos un paquete de café artesanal local. ¡La combinación perfecta!",
            fechaFin = LocalDate.of(2025, 12, 1),
            precio = 40000.0,
            imagenResId = R.drawable.pr003
        ),
        Promocion(
            id = "OFR-004",
            nombre = "La Hora del Postre Familiar 👨‍👩‍👧‍👦",
            descripcion = "Cualquier torta completa + 6 cupcakes (a elección) por solo $65.000. Ahorras $15.000.",
            fechaFin = LocalDate.of(2025, 12, 10),
            precio = 65000.0,
            imagenResId = R.drawable.pr004
        )
    )
}