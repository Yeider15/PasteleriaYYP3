package com.example.pasteleriayy.data.repository

import com.example.pasteleriayy.data.remote.ApiRecetaService
import com.example.pasteleriayy.data.remote.RetrofitRecetaClient
import com.example.pasteleriayy.model.RecetaResponse

class RecetaRepository {

    private val api = RetrofitRecetaClient.instance.create(ApiRecetaService::class.java)

    suspend fun buscar(nombre: String): RecetaResponse {
        return api.buscarRecetas(nombre)
    }
}