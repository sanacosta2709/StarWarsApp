package com.example.starwarsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val StarWarsTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontSize = 32.sp,
        color = Color.White
    ),
    headlineMedium = TextStyle(
        fontFamily = StarWarsFont,
        fontSize = 28.sp,
        color = Color.White
    ),
    titleLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontSize = 22.sp,
        color = Color.White
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        color = Color(0xFFE0E0E0)
    )
)

