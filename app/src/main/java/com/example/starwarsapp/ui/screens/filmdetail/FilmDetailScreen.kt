package com.example.starwarsapp.ui.screens.filmdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.viewmodel.FilmDetailViewModel
import com.example.starwarsapp.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    filmId: Int
) {
    LaunchedEffect(filmId) {
        viewModel.loadFilm(filmId)
    }

    val state by viewModel.filmDetailState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle de Película") }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (state) {

                is NetworkResult.Loading ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))

                is NetworkResult.Error ->
                    Text(
                        (state as NetworkResult.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                is NetworkResult.Success -> {
                    val film = (state as NetworkResult.Success).data

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(film.title, style = MaterialTheme.typography.titleLarge)
                        Text("Episodio: ${film.episodeId}")
                        Text("Director: ${film.director}")
                        Text("Productores: ${film.producer}")
                        Text("Fecha de lanzamiento: ${film.releaseDate}")
                        Text("Sinopsis:\n${film.openingCrawl}")
                    }
                }
            }
        }
    }
}
