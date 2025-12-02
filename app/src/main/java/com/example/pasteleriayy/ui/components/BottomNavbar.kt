package com.example.pasteleriayy.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)
private val navItems = listOf(
    NavItem("menu", "Menú", Icons.Filled.Fastfood),
    NavItem("promociones", "Promocion", Icons.Filled.Discount),
    NavItem("contacto", "Contacto", Icons.Filled.ContactMail),
    NavItem("apiRecetas", "API", Icons.Filled.Fastfood),
    NavItem("registro", "Registro", Icons.Filled.AppRegistration)
)

@Composable
fun BottomNavBar(
    navController: NavController,
    currentRoute: String? = null
) {
    val itemColors = NavigationBarItemDefaults.colors(
        unselectedTextColor = Color.White,
        unselectedIconColor = Color.White,
        selectedTextColor = Color.White,
        selectedIconColor = Color.White,
        indicatorColor = Color(0xFFE57373)
    )

    NavigationBar(
        containerColor = Color(0xFFEC407A),
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = itemColors
            )
        }
    }
}
