package com.example.starwarsapp.ui.screens.people

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.utils.NetworkResult
import com.example.starwarsapp.viewmodel.PeopleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
    viewModel: PeopleViewModel,
    onPersonClick: (Int) -> Unit      // 👈🔥 NUEVO: ya no usamos NavController
) {
    val state by viewModel.peopleState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Personajes") }
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

                    val people = (state as NetworkResult.Success<List<Person>>).data

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(people) { person ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                        // 🔥 Extraer ID desde la URL
                                        val personId = person.url
                                            .trimEnd('/')
                                            .split("/")
                                            .last()
                                            .toInt()

                                        // 🔥 Llamar al callback de navegación
                                        onPersonClick(personId)
                                    },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = person.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Género: ${person.gender}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
