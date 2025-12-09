package com.example.starwarsapp.ui.screens.vehicles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.viewmodel.VehiclesViewModel
import com.example.starwarsapp.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesListScreen(viewModel: VehiclesViewModel) {

    val state by viewModel.vehiclesState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Vehículos") })
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (state) {

                is NetworkResult.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is NetworkResult.Error -> {
                    Text(
                        text = (state as NetworkResult.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is NetworkResult.Success -> {
                    val vehicles = (state as NetworkResult.Success<List<*>>)
                        .data.filterIsInstance<com.example.starwarsapp.data.model.Vehicle>()

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(vehicles) { vehicle ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(vehicle.name, style = MaterialTheme.typography.titleMedium)
                                    Text("Modelo: ${vehicle.model}")
                                    Text("Fabricante: ${vehicle.manufacturer}")
                                    Text("Tripulación: ${vehicle.crew}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
