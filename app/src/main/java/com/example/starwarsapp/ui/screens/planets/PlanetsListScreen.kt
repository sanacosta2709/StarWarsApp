package com.example.starwarsapp.ui.screens.planets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.data.model.Planet
import com.example.starwarsapp.viewmodel.PlanetsViewModel
import com.example.starwarsapp.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetsListScreen(
    viewModel: PlanetsViewModel
) {
    val state by viewModel.planetsState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Star Wars Planets") })
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when (state) {

                is NetworkResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is NetworkResult.Error -> {
                    Text(
                        text = (state as NetworkResult.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is NetworkResult.Success -> {
                    val planets = (state as NetworkResult.Success<List<Planet>>).data

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(planets) { planet ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(Modifier.padding(16.dp)) {

                                    Text(planet.name, style = MaterialTheme.typography.titleMedium)
                                    Spacer(Modifier.height(8.dp))

                                    Text("Climate: ${planet.climate}")
                                    Text("Terrain: ${planet.terrain}")
                                    Text("Population: ${planet.population}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
