package com.example.pasteleriayy.data.remote

import com.example.pasteleriayy.model.Usuario
import com.example.pasteleriayy.model.LoginRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/registro/login")
    suspend fun login(@Body request: LoginRequest): Response<Usuario>

    @POST("api/registro")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @GET("api/registro/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Long): Response<Usuario>

    @PUT("api/registro/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: Long,
        @Body usuario: Usuario
    ): Response<Usuario>

    @DELETE("api/registro/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): Response<Void>
}
