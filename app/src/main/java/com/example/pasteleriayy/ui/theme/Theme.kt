package com.example.pasteleriayy.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = RosaPastel,
    secondary = MarronClaro,
    background = Crema,
    surface = Blanco,
    onPrimary = MarronChocolate,
    onSecondary = MarronChocolate,
    onBackground = MarronChocolate,
    onSurface = MarronChocolate
)

@Composable
fun PasteleriaYYTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
