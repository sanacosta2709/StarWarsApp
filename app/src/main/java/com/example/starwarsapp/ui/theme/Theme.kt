package com.example.starwarsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val StarWarsDarkColors = darkColorScheme(
    primary = Color(0xFFFFE81F),
    secondary = Color(0xFF4A90E2),
    tertiary = Color(0xFFEB5757),
    background = Color(0xFF000000),
    surface = Color(0xFF0A0A0A),
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color(0xFFE0E0E0)
)

@Composable
fun StarWarsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StarWarsDarkColors,
        typography = StarWarsTypography,
        shapes = StarWarsShapes,
        content = content
    )
}
