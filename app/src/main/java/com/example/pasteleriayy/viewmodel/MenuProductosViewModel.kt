package com.example.pasteleriayy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pasteleriayy.data.local.ProductosDataSource
import com.example.pasteleriayy.model.Producto

class MenuProductosViewModel : ViewModel() {
    val productos: List<Producto> = ProductosDataSource.getListaProductos()
}