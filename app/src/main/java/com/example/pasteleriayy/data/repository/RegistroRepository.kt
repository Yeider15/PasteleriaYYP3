package com.example.pasteleriayy.data.repository

import com.example.pasteleriayy.data.remote.ApiService
import com.example.pasteleriayy.data.remote.RetrofitClient
import com.example.pasteleriayy.model.LoginRequest
import com.example.pasteleriayy.model.Usuario
import retrofit2.Response

class UsuarioRepository {

    private val api = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun login(request: LoginRequest): Response<Usuario> =
        api.login(request)

    suspend fun crear(usuario: Usuario): Response<Usuario> =
        api.registrarUsuario(usuario)

    suspend fun obtener(id: Long): Response<Usuario> =
        api.obtenerUsuario(id)

    suspend fun actualizar(id: Long, usuario: Usuario): Response<Usuario> =
        api.actualizarUsuario(id, usuario)

    suspend fun eliminar(id: Long): Response<Void> =
        api.eliminarUsuario(id)
}
