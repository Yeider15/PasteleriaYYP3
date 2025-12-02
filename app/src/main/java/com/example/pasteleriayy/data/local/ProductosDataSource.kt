package com.example.pasteleriayy.data.local

import com.example.pasteleriayy.R
import com.example.pasteleriayy.model.Producto

object ProductosDataSource {

    fun getListaProductos(): List<Producto> = listOf(
        Producto(
            "TC001", "Torta Cuadrada de Chocolate",
            "Deliciosa torta de chocolate con capas de ganache...",
            45000.0, R.drawable.tc001
        ),

        Producto(
            "TC002", "Torta Cuadrada de Frutas",
            "Una mezcla de frutas frescas y crema chantilly...",
            50000.0, R.drawable.tc002
        ),

        Producto(
            "TT001", "Torta Circular de Vainilla",
            "Bizcocho de vainilla clásico relleno con crema...",
            40000.0, R.drawable.tt001
        ),

        Producto(
            "TT002", "Torta Circular de Manjar",
            "Torta tradicional chilena con manjar y nueces...",
            42000.0, R.drawable.tt002
        ),

        Producto(
            "P1001", "Mousse de Chocolate",
            "Postre individual cremoso y suave...",
            5000.0, R.drawable.p1001
        ),

        Producto(
            "P1002", "Tiramisú Clásico",
            "Un postre italiano individual con capas de café, mascarpone y cacao...",
            5500.0, R.drawable.p1002
        ),

        Producto(
            "PSA001", "Torta Sin Azúcar de Naranja",
            "Torta ligera y deliciosa, endulzada naturalmente...",
            48000.0, R.drawable.psa001
        ),

        Producto(
            "PSA002", "Cheesecake Sin Azúcar",
            " Suave y cremoso...",
            47000.0, R.drawable.psa002
        )
    )
}