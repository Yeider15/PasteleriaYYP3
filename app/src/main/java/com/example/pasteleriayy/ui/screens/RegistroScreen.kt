package com.example.pasteleriayy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriayy.model.Usuario
import com.example.pasteleriayy.viewmodel.RegistroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioValidacion(
    navController: NavController,
    viewModel: RegistroViewModel,
    modifier: Modifier = Modifier
) {

    val mensaje by viewModel.mensaje.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var nombre by remember { mutableStateOf("") }
            var correo by remember { mutableStateOf("") }
            var contrasena by remember { mutableStateOf("") }
            var telefono by remember { mutableStateOf("") }

            var nombreError by remember { mutableStateOf("") }
            var correoError by remember { mutableStateOf("") }
            var contrasenaError by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; nombreError = "" },
                isError = nombreError.isNotEmpty(),
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            if (nombreError.isNotEmpty()) Text(nombreError, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; correoError = "" },
                isError = correoError.isNotEmpty(),
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            if (correoError.isNotEmpty()) Text(correoError, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it; contrasenaError = "" },
                isError = contrasenaError.isNotEmpty(),
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (contrasenaError.isNotEmpty()) Text(contrasenaError, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono (opcional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    nombreError = ""
                    correoError = ""
                    contrasenaError = ""

                    if (nombre.isBlank()) nombreError = "Ingrese un nombre"
                    if (correo.isBlank()) correoError = "Ingrese un correo"
                    if (!correo.contains("@")) correoError = "Correo inválido"
                    if (contrasena.length < 6) contrasenaError = "Mínimo 6 caracteres"

                    val valido = nombreError.isEmpty() && correoError.isEmpty() && contrasenaError.isEmpty()

                    if (valido) {
                        val user = Usuario(
                            id = null,
                            nombre = nombre,
                            correo = correo,
                            contrasena = contrasena,
                            telefono = if (telefono.isNotBlank()) telefono else null
                        )
                        viewModel.registrarUsuario(user) { exito ->
                            if (exito) {
                                navController.navigate("login") {
                                    popUpTo("registro") { inclusive = true }
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Usuario")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("registro") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = mensaje,
                    color = if (mensaje.contains("éxito", ignoreCase = true)) Color.Green else Color.Red
                )
            }
        }
    }
}
