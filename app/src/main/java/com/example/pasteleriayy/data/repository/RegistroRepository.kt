package com.example.pasteleriayy.data.repository

import com.example.pasteleriayy.data.remote.ApiService
import com.example.pasteleriayy.data.remote.RetrofitClient
import com.example.pasteleriayy.model.Usuario

class UsuarioRepository {

    private val api = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun listar(): List<Usuario> = api.listarUsuarios()

    suspend fun obtener(id: Long): Usuario = api.obtenerUsuario(id)

    suspend fun crear(usuario: Usuario) = api.registrarUsuario(usuario)

    suspend fun actualizar(id: Long, usuario: Usuario) =
        api.actualizarUsuario(id, usuario)

    suspend fun eliminar(id: Long) = api.eliminarUsuario(id)
}