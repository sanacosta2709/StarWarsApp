package com.example.starwarsapp.ui.screens.films

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment              // 👈 FALTABA ESTE IMPORT
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.ui.navigation.AppRoutes
import com.example.starwarsapp.utils.NetworkResult
import com.example.starwarsapp.viewmodel.FilmsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsListScreen(
    navController: NavController,
    viewModel: FilmsViewModel
) {
    val state by viewModel.filmsState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Películas") }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
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
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is NetworkResult.Success -> {

                    val films = (state as NetworkResult.Success<List<Film>>).data

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(films) { film ->

                            // Extraer ID desde la URL
                            val filmId = film.url
                                .trimEnd('/')
                                .split("/")
                                .last()
                                .toInt()

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("${AppRoutes.FILM_DETAIL}/$filmId")
                                    },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = film.title,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text("Episodio: ${film.episodeId}")
                                    Text("Director: ${film.director}")
                                    Text("Lanzamiento: ${film.releaseDate}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
