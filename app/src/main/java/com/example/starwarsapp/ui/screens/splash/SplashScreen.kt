package com.example.starwarsapp.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsapp.R
import com.example.starwarsapp.ui.navigation.AppRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    //------------------------------
    // Estado de animación
    //------------------------------
    var visible by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1800)
    )

    val scaleAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0.6f,
        animationSpec = tween(durationMillis = 1800)
    )

    //------------------------------
    // Lanzar animación + navegar
    //------------------------------
    LaunchedEffect(true) {
        visible = true
        delay(2500) // espera final de animación
        navController.navigate(AppRoutes.HOME) {
            popUpTo(AppRoutes.SPLASH) { inclusive = true }
        }
    }

    //------------------------------
    // UI Splash
    //------------------------------
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.starwars_logo),  // ⬅ tu logo aquí
            contentDescription = "Star Wars Logo",
            modifier = Modifier
                .size(260.dp)
                .alpha(alphaAnim.value)
                .scale(scaleAnim.value)
        )
    }
}
