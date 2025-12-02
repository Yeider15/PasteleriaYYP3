package com.example.pasteleriayy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pasteleriayy.data.local.PromocionesDataSource
import com.example.pasteleriayy.model.Promocion

class PromocionesViewModel : ViewModel() {
    val promociones: List<Promocion> = PromocionesDataSource.getListaPromociones()
}