package com.example.pasteleriayy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriayy.data.repository.UsuarioRepository
import com.example.pasteleriayy.model.LoginRequest
import com.example.pasteleriayy.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val repo = UsuarioRepository()

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje

    private val _usuarioLogeado = MutableStateFlow<Usuario?>(null)
    val usuarioLogeado: StateFlow<Usuario?> = _usuarioLogeado


    fun login(correo: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.login(LoginRequest(correo, contrasena))
                if (response.isSuccessful && response.body() != null) {
                    _usuarioLogeado.value = response.body()
                    _mensaje.value = "Inicio de sesión exitoso"
                    onResult(true)
                } else {
                    _mensaje.value = "Credenciales incorrectas"
                    onResult(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error de conexión"
                onResult(false)
            }
        }
    }


    fun registrarUsuario(usuario: Usuario, onFinish: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.crear(usuario)
                if (response.isSuccessful) {
                    _mensaje.value = "Usuario registrado con éxito"
                    onFinish(true)
                } else {
                    _mensaje.value = "Error al registrar"
                    onFinish(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error al registrar"
                onFinish(false)
            }
        }
    }


    fun actualizarUsuario(id: Long, usuario: Usuario, onFinish: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.actualizar(id, usuario)
                if (response.isSuccessful) {
                    _usuarioLogeado.value = response.body()
                    _mensaje.value = "Usuario actualizado"
                    onFinish(true)
                } else {
                    _mensaje.value = "Error al actualizar"
                    onFinish(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error de conexión"
                onFinish(false)
            }
        }
    }


    fun eliminarCuenta(id: Long, onFinish: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.eliminar(id)
                if (response.isSuccessful) {
                    _usuarioLogeado.value = null
                    _mensaje.value = "Cuenta eliminada"
                    onFinish(true)
                } else {
                    _mensaje.value = "Error al eliminar"
                    onFinish(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error de conexión"
                onFinish(false)
            }
        }
    }
    fun cerrarSesion() {
        _usuarioLogeado.value = null
        _mensaje.value = "Sesión cerrada"
    }



}
