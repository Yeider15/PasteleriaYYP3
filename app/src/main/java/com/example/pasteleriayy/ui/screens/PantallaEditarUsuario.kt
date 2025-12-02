package com.example.pasteleriayy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.model.Usuario
import com.example.pasteleriayy.viewmodel.RegistroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarUsuario(
    idUsuario: Long,
    navController: NavController,
    viewModel: RegistroViewModel = viewModel()
) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var cargando by remember { mutableStateOf(true) }

    LaunchedEffect(idUsuario) {
        viewModel.obtenerUsuario(idUsuario) { usuario ->
            usuario?.let {
                nombre = it.nombre
                correo = it.correo
                contrasena = it.contrasena
                telefono = it.telefono ?: ""
            }
            cargando = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Usuario") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFC8DD),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { padding ->

        if (cargando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val usuarioActualizado = Usuario(
                        id = idUsuario,
                        nombre = nombre,
                        correo = correo,
                        contrasena = contrasena,
                        telefono = if (telefono.isBlank()) null else telefono
                    )

                    viewModel.actualizarUsuario(idUsuario, usuarioActualizado) { ok ->
                        if (ok) {
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}
