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
import com.example.pasteleriayy.ui.components.PromocionItem
import com.example.pasteleriayy.viewmodel.PromocionesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromocionesScreen(
    navController: NavController,
    promocionesViewModel: PromocionesViewModel = viewModel()
) {
    val promociones = promocionesViewModel.promociones

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Ofertas y Promociones 🎉") },
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
            items(promociones) { promocion ->
                PromocionItem(
                    promocion = promocion,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}