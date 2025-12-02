package com.example.pasteleriayy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriayy.data.repository.UsuarioRepository
import com.example.pasteleriayy.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val repo = UsuarioRepository()

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje

    fun cargarUsuarios() {
        viewModelScope.launch {
            try {
                _usuarios.value = repo.listar()
            } catch (e: Exception) {
                _mensaje.value = "Error al cargar usuarios"
            }
        }
    }

    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                repo.crear(usuario)
                _mensaje.value = "Usuario registrado con éxito"
            } catch (e: Exception) {
                _mensaje.value = "Error al registrar usuario"
            }
        }
    }

    fun obtenerUsuario(id: Long, onResult: (Usuario?) -> Unit) {
        viewModelScope.launch {
            try {
                val usuario = repo.obtener(id)
                onResult(usuario)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun actualizarUsuario(id: Long, usuario: Usuario, onFinish: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.actualizar(id, usuario)
                if (response.isSuccessful) {
                    _mensaje.value = "Usuario actualizado"
                    cargarUsuarios()
                    onFinish(true)
                } else {
                    _mensaje.value = "Error al actualizar usuario"
                    onFinish(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error al conectar con el servidor"
                onFinish(false)
            }
        }
    }



    fun eliminar(id: Long) {
        viewModelScope.launch {
            try {
                repo.eliminar(id)
                cargarUsuarios()
                _mensaje.value = "Usuario eliminado"
            } catch (e: Exception) {
                _mensaje.value = "Error al eliminar"
            }
        }
    }
}
