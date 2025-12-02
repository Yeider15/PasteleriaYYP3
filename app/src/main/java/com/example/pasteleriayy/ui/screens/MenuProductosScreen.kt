package com.example.pasteleriayy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.ui.components.ProductoItem
import com.example.pasteleriayy.viewmodel.MenuProductosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuProductosScreen(
    navController: NavController,
    menuProductosViewModel: MenuProductosViewModel = viewModel()
) {

    val productos = menuProductosViewModel.productos

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Nuestro Menú de Postres") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            items(productos) { producto ->
                ProductoItem(
                    producto = producto,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}