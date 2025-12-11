package com.example.pasteleriayy

import com.example.pasteleriayy.data.repository.UsuarioRepository
import com.example.pasteleriayy.model.Usuario
import com.example.pasteleriayy.viewmodel.RegistroViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RegistroViewModelTest {

    @Mock
    private lateinit var repository: UsuarioRepository

    private lateinit var viewModel: RegistroViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = RegistroViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loginSiEsExitosoDebeGuardarUsuarioYMensajeDeExito() = runTest {
        val correo = "test@mail.com"
        val pass = "123456"
        val usuarioMock = Usuario(1, "Yeider", correo, pass, "999")

        whenever(repository.login(any())).thenReturn(Response.success(usuarioMock))

        viewModel.login(correo, pass) { }

        assertEquals("Inicio de sesión exitoso", viewModel.mensaje.value)
        assertNotNull(viewModel.usuarioLogeado.value)
        assertEquals("Yeider", viewModel.usuarioLogeado.value?.nombre)
    }

    @Test
    fun loginSiFallaDebeMostrarMensajeDeError() = runTest {
        whenever(repository.login(any())).thenReturn(Response.success(null))

        viewModel.login("correo@malo.com", "pass_mala") { }

        assertEquals("Credenciales incorrectas", viewModel.mensaje.value)
        assertNull(viewModel.usuarioLogeado.value)
    }

    @Test
    fun registrarUsuarioSiEsExitosoActualizaElMensaje() = runTest {
        val nuevoUsuario = Usuario(null, "Yaqui", "yaqui@mail.com", "abc")
        whenever(repository.crear(any())).thenReturn(Response.success(nuevoUsuario))

        viewModel.registrarUsuario(nuevoUsuario) { }

        Mockito.verify(repository).crear(nuevoUsuario)
        assertEquals("Usuario registrado con éxito", viewModel.mensaje.value)
    }

    @Test
    fun eliminarCuentaSiEsExitosoLimpiaElUsuarioLogeado() = runTest {
        val id = 1L
        whenever(repository.eliminar(id)).thenReturn(Response.success(null))

        viewModel.eliminarCuenta(id) { }

        assertEquals("Cuenta eliminada", viewModel.mensaje.value)
        assertNull(viewModel.usuarioLogeado.value)
    }

    @Test
    fun errorDeRedDebeCapturarExcepcionYMostrarMensaje() = runTest {
        whenever(repository.login(any())).thenThrow(RuntimeException("Fallo de red"))

        viewModel.login("a", "b") { }

        assertEquals("Error de conexión", viewModel.mensaje.value)
    }
}