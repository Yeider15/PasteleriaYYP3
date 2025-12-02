package com.example.pasteleriayy

import com.example.pasteleriayy.data.local.ProductosDataSource
import com.example.pasteleriayy.data.local.PromocionesDataSource
import com.example.pasteleriayy.model.*
import com.example.pasteleriayy.viewmodel.MenuProductosViewModel
import com.example.pasteleriayy.viewmodel.PromocionesViewModel
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class PasteleriaUnitTest {

    @Test
    fun modeloUsuario_guardaDatosCorrectamente() {
        val usuario = Usuario(
            id = 1,
            nombre = "Yeider",
            correo = "yeider@duoc.cl",
            contrasena = "123456",
            telefono = "99999999"
        )

        assertEquals("Yeider", usuario.nombre)
        assertEquals("yeider@duoc.cl", usuario.correo)
        assertNotNull(usuario.telefono)
    }

    @Test
    fun modeloProducto_estructuraCorrecta() {
        val producto = Producto(
            id = "TC001",
            nombre = "Torta Chocolate",
            descripcion = "Deliciosa...",
            precio = 45000.0,
            imagenResId = 0
        )

        assertTrue(producto.precio > 0)
        assertEquals("TC001", producto.id)
    }

    @Test
    fun modeloReceta_manejoDeNulos() {
        val receta = Receta(
            strMeal = "Pie de Limon",
            strInstructions = null,
            strMealThumb = null
        )

        assertNull(receta.strInstructions)
        assertEquals("Pie de Limon", receta.strMeal)
    }


    @Test
    fun productosDataSource_traeListaValida() {
        val lista = ProductosDataSource.getListaProductos()

        assertFalse(lista.isEmpty())

        val primerProducto = lista[0]
        assertEquals("Torta Cuadrada de Chocolate", primerProducto.nombre)
        assertTrue(primerProducto.precio > 1000)
    }

    @Test
    fun promocionesDataSource_traeListaValida() {
        val lista = PromocionesDataSource.getListaPromociones()

        assertFalse(lista.isEmpty())
        val primeraPromo = lista[0]
        assertNotNull(primeraPromo.fechaFin)
    }

    @Test
    fun menuViewModel_inicializaCorrectamente() {
        val viewModel = MenuProductosViewModel()

        assertNotNull(viewModel.productos)
        assertTrue(viewModel.productos.isNotEmpty())
    }

    @Test
    fun promocionesViewModel_inicializaCorrectamente() {
        val viewModel = PromocionesViewModel()

        assertNotNull(viewModel.promociones)
        assertTrue(viewModel.promociones.isNotEmpty())
    }
}