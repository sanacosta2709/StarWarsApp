package com.example.starwarsapp.ui.screens.persondetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.viewmodel.PersonDetailViewModel
import com.example.starwarsapp.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(
    viewModel: PersonDetailViewModel,
    personId: Int
) {
    LaunchedEffect(personId) {
        viewModel.loadPerson(personId)
    }

    val state by viewModel.personDetailState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle del personaje") }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                    val person = (state as NetworkResult.Success).data

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text("Nombre: ${person.name}", style = MaterialTheme.typography.titleLarge)
                        Text("Género: ${person.gender}")
                        Text("Altura: ${person.height}")
                        Text("Peso: ${person.mass}")
                        Text("Cabello: ${person.hair_color}")
                        Text("Ojos: ${person.eye_color}")
                        Text("Nacimiento: ${person.birth_year}")

                    }
                }
            }
        }
    }
}
