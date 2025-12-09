package com.example.starwarsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.starwarsapp.data.remote.StarWarsApiService
import com.example.starwarsapp.data.repository.StarWarsRepositoryImpl
import com.example.starwarsapp.ui.navigation.AppNavHost
import com.example.starwarsapp.ui.theme.StarWarsTheme
import com.example.starwarsapp.viewmodel.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(StarWarsApiService::class.java)

        // Repositorio único
        val repository = StarWarsRepositoryImpl(apiService)

        // ViewModels con el repositorio
        val peopleViewModel = PeopleViewModel(repository)
        val filmsViewModel = FilmsViewModel(repository)
        val planetsViewModel = PlanetsViewModel(repository)
        val vehiclesViewModel = VehiclesViewModel(repository)
        val starshipsViewModel = StarshipsViewModel(repository)
        val personDetailViewModel = PersonDetailViewModel(repository)
        val filmDetailViewModel = FilmDetailViewModel(repository)

        setContent {

            // APLICACIÓN COMPLETA CON TEMA STAR WARS ⭐
            StarWarsTheme {

                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    peopleViewModel = peopleViewModel,
                    filmsViewModel = filmsViewModel,
                    planetsViewModel = planetsViewModel,
                    vehiclesViewModel = vehiclesViewModel,
                    starshipsViewModel = starshipsViewModel,
                    personDetailViewModel = personDetailViewModel,
                    filmDetailViewModel = filmDetailViewModel
                )
            }
        }
    }
}
