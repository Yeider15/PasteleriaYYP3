package com.example.pasteleriayy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pasteleriayy.navigation.AppNavigation
import com.example.pasteleriayy.ui.components.BottomNavBar
import com.example.pasteleriayy.ui.theme.PasteleriaYYTheme
import com.example.pasteleriayy.viewmodel.RegistroViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PasteleriaYYTheme {
                val registroViewModel: RegistroViewModel = viewModel()

                MainAppStructure(registroViewModel)
            }
        }
    }
}

@Composable
fun MainAppStructure(registroViewModel: RegistroViewModel) {

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        AppNavigation(
            navController = navController,
            viewModel = registroViewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
