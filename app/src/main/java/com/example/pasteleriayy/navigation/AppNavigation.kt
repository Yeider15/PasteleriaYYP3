package com.example.pasteleriayy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.pasteleriayy.ui.screens.*
import com.example.pasteleriayy.viewmodel.RegistroViewModel

object AppScreens {
    const val MENU = "menu"
    const val REGISTRO = "registro"
    const val LOGIN = "login"
    const val CONTACTO = "contacto"
    const val PROMOCIONES = "promociones"
    const val API_RECETAS = "apiRecetas"
    const val PERFIL_USUARIO = "perfil"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: RegistroViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.MENU,
        modifier = modifier
    ) {

        composable(AppScreens.MENU) {
            MenuProductosScreen(navController = navController)
        }

        composable(AppScreens.REGISTRO) {
            FormularioValidacion(navController = navController, viewModel = viewModel)
        }

        composable(AppScreens.LOGIN) {
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        composable(AppScreens.CONTACTO) {
            ContactoScreen(navController = navController)
        }

        composable(AppScreens.PROMOCIONES) {
            PromocionesScreen(navController = navController)
        }

        composable(AppScreens.API_RECETAS) {
            RecetasScreen(navController = navController)
        }

        composable(AppScreens.PERFIL_USUARIO) {
            PantallaPerfilUsuario(navController = navController, viewModel = viewModel)
        }
    }
}
