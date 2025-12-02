package com.example.pasteleriayy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriayy.data.repository.RecetaRepository
import com.example.pasteleriayy.model.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {

    private val repo = RecetaRepository()

    private val _recetas = MutableStateFlow<List<Receta>>(emptyList())
    val recetas: StateFlow<List<Receta>> = _recetas

    fun buscar(nombre: String) {
        viewModelScope.launch {
            try {
                val res = repo.buscar(nombre)
                _recetas.value = res.meals ?: emptyList()
            } catch (e: Exception) {
                _recetas.value = emptyList()
            }
        }
    }
}
