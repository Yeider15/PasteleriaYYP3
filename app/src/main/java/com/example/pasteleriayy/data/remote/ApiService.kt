package com.example.pasteleriayy.data.remote

import com.example.pasteleriayy.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("api/registro")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @GET("api/registro")
    suspend fun listarUsuarios(): List<Usuario>

    @GET("api/registro/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Long): Usuario

    @PUT("api/registro/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: Long,
        @Body usuario: Usuario
    ): Response<Usuario>

    @DELETE("api/registro/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): Response<Void>
}