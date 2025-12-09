package com.example.starwarsapp.ui.screens.starships

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.viewmodel.StarshipsViewModel
import com.example.starwarsapp.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarshipsListScreen(viewModel: StarshipsViewModel) {

    val state by viewModel.starshipsState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Starships") })
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
                    val starships = (state as NetworkResult.Success<List<*>>)
                        .data.filterIsInstance<com.example.starwarsapp.data.model.Starship>()

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(starships) { ship ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(ship.name, style = MaterialTheme.typography.titleMedium)
                                    Text("Model: ${ship.model}")
                                    Text("Crew: ${ship.crew}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
