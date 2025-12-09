package com.example.starwarsapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwarsapp.R

@Composable
fun HomeScreen(
    onPeopleClick: () -> Unit,
    onFilmsClick: () -> Unit,
    onPlanetsClick: () -> Unit,
    onStarshipsClick: () -> Unit,
    onVehiclesClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "STAR WARS UNIVERSE\nEXPLORER",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🔥 AQUÍ VA TU IMAGEN DEL SOLDADO (reemplaza al ícono de Android)
            Image(
                painter = painterResource(id = R.drawable.soldado),
                contentDescription = "Stormtrooper Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            HomeButton(text = "PERSONAJES", onClick = onPeopleClick)
            HomeButton(text = "PELÍCULAS", onClick = onFilmsClick)
            HomeButton(text = "PLANETAS", onClick = onPlanetsClick)
            HomeButton(text = "NAVES ESPACIALES", onClick = onStarshipsClick)
            HomeButton(text = "VEHÍCULOS", onClick = onVehiclesClick)
        }
    }
}

@Composable
fun HomeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}
