package com.example.pasteleriayy.ui.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.model.Usuario
import com.example.pasteleriayy.viewmodel.RegistroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrud(
    navController: NavController,
    viewModel: RegistroViewModel = viewModel()
) {
    val usuarios by viewModel.usuarios.collectAsState()
    val mensaje by viewModel.mensaje.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.cargarUsuarios()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Administrar Usuarios") },

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
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(16.dp)
        ) {

            if (mensaje.isNotEmpty()) {
                Text(
                    text = mensaje,
                    color = if (mensaje.contains("exito", true)) Color(0xFF2E7D32) else Color.Red
                )
                Spacer(Modifier.height(8.dp))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(usuarios) { usuario ->
                    UsuarioCard(
                        usuario = usuario,
                        onEliminar = { id ->
                            vibrarTelefono(context)
                            viewModel.eliminar(id)
                        },
                        onEditar = { id -> navController.navigate("editar_usuario/$id") }
                    )
                }
            }
        }
    }
}

fun vibrarTelefono(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(200)
    }
}

@Composable
fun UsuarioCard(
    usuario: Usuario,
    onEliminar: (Long) -> Unit,
    onEditar: (Long) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(16.dp)) {

            Text("ID: ${usuario.id}")
            Text("Nombre: ${usuario.nombre}")
            Text("Correo: ${usuario.correo}")
            Text("Teléfono: ${usuario.telefono ?: "No registrado"}")

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = { onEditar(usuario.id!!) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Editar")
                }

                Spacer(Modifier.width(10.dp))

                Button(
                    onClick = { onEliminar(usuario.id!!) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}