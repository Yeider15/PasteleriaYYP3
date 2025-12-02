package com.example.pasteleriayy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriayy.R
import com.example.pasteleriayy.ui.theme.Crema

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactoScreen(navController: NavController, modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf("") }
    var correoError by remember { mutableStateOf("") }
    var telefonoError by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    var mensajeExito by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacto") },


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
                .background(Crema)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(140.dp)
                    .padding(top = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Formulario de Contacto",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = ""
                },
                label = { Text("Nombre") },
                isError = nombreError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
            if (nombreError.isNotEmpty()) {
                Text(text = nombreError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    correoError = ""
                },
                label = { Text("Correo") },
                isError = correoError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
            if (correoError.isNotEmpty()) {
                Text(text = correoError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = {
                    telefono = it
                    telefonoError = ""
                },
                label = { Text("Teléfono") },
                isError = telefonoError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
            if (telefonoError.isNotEmpty()) {
                Text(text = telefonoError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = mensaje,
                onValueChange = {
                    mensaje = it
                    mensajeError = ""
                },
                label = { Text("Mensaje") },
                isError = mensajeError.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            if (mensajeError.isNotEmpty()) {
                Text(text = mensajeError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    nombreError = ""
                    correoError = ""
                    telefonoError = ""
                    mensajeError = ""
                    mensajeExito = ""

                    if (nombre.isBlank()) nombreError = "El nombre no puede estar vacío"
                    if (correo.isBlank()) correoError = "El correo no puede estar vacío"
                    else if (!correo.contains("@")) correoError = "Correo inválido"
                    if (telefono.isBlank()) telefonoError = "El teléfono no puede estar vacío"
                    if (mensaje.isBlank()) mensajeError = "Debe escribir un mensaje"

                    if (nombreError.isEmpty() && correoError.isEmpty() &&
                        telefonoError.isEmpty() && mensajeError.isEmpty()
                    ) {
                        mensajeExito = "Mensaje enviado correctamente 🎉"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar")
            }

            if (mensajeExito.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = mensajeExito, color = Color(0xFF388E3C))
            }
        }
    }
}
