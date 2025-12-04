package com.example.pasteleriayy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriayy.viewmodel.RegistroViewModel
import com.example.pasteleriayy.model.Usuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfilUsuario(
    navController: NavController,
    viewModel: RegistroViewModel     // ← 🔥 YA NO CREA viewModel()
) {
    val usuario = viewModel.usuarioLogeado.collectAsState().value
    val mensaje = viewModel.mensaje.collectAsState().value

    var mostrandoLoading by remember { mutableStateOf(false) }
    var snackbarVisible by remember { mutableStateOf(false) }


    if (usuario == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }


    var nombre by remember { mutableStateOf(usuario.nombre) }
    var correo by remember { mutableStateOf(usuario.correo) }
    var contrasena by remember { mutableStateOf(usuario.contrasena) }
    var telefono by remember { mutableStateOf(usuario.telefono ?: "") }

    // Validaciones simples
    var errorNombre by remember { mutableStateOf(false) }
    var errorCorreo by remember { mutableStateOf(false) }
    var errorContrasena by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Perfil") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFC8DD)
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        LaunchedEffect(snackbarVisible) {
            if (snackbarVisible) {
                snackbarHostState.showSnackbar(mensaje)
                snackbarVisible = false
            }
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    errorNombre = it.isBlank()
                },
                isError = errorNombre,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            if (errorNombre) Text("El nombre es obligatorio", color = Color.Red)


            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    errorCorreo = !it.contains("@")
                },
                isError = errorCorreo,
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            if (errorCorreo) Text("Correo inválido", color = Color.Red)


            OutlinedTextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                    errorContrasena = contrasena.length < 4
                },
                isError = errorContrasena,
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (errorContrasena) Text("La contraseña debe tener mínimo 4 caracteres", color = Color.Red)


            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono (opcional)") },
                modifier = Modifier.fillMaxWidth()
            )


            Button(
                onClick = {
                    if (nombre.isBlank() || !correo.contains("@") || contrasena.length < 4) {
                        snackbarVisible = true
                        return@Button
                    }

                    mostrandoLoading = true

                    val actualizado = Usuario(
                        id = usuario.id,
                        nombre = nombre,
                        correo = correo,
                        contrasena = contrasena,
                        telefono = if (telefono.isBlank()) null else telefono
                    )

                    viewModel.actualizarUsuario(usuario.id!!, actualizado) { ok ->
                        mostrandoLoading = false
                        snackbarVisible = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
            ) {
                Text("Guardar Cambios")
            }

            if (mostrandoLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }


            Button(
                onClick = {
                    viewModel.eliminarCuenta(usuario.id!!) { ok ->
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text("Eliminar Cuenta")
            }


            TextButton(
                onClick = {
                    viewModel.cerrarSesion()
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar Sesión", color = Color.Gray)
            }
        }
    }
}
