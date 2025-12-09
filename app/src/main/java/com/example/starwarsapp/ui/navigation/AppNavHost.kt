package com.example.starwarsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Screens
import com.example.starwarsapp.ui.screens.home.HomeScreen
import com.example.starwarsapp.ui.screens.people.PeopleListScreen
import com.example.starwarsapp.ui.screens.films.FilmsListScreen
import com.example.starwarsapp.ui.screens.planets.PlanetsListScreen
import com.example.starwarsapp.ui.screens.starships.StarshipsListScreen
import com.example.starwarsapp.ui.screens.vehicles.VehiclesListScreen
import com.example.starwarsapp.ui.screens.persondetail.PersonDetailScreen
import com.example.starwarsapp.ui.screens.filmdetail.FilmDetailScreen

// ViewModels
import com.example.starwarsapp.viewmodel.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    peopleViewModel: PeopleViewModel,
    filmsViewModel: FilmsViewModel,
    planetsViewModel: PlanetsViewModel,
    starshipsViewModel: StarshipsViewModel,
    vehiclesViewModel: VehiclesViewModel,
    personDetailViewModel: PersonDetailViewModel,
    filmDetailViewModel: FilmDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {

        // ----------------------------------------
        // HOME
        // ----------------------------------------
        composable(AppRoutes.HOME) {
            HomeScreen(
                onPeopleClick = { navController.navigate(AppRoutes.PEOPLE) },
                onFilmsClick = { navController.navigate(AppRoutes.FILMS) },
                onPlanetsClick = { navController.navigate(AppRoutes.PLANETS) },
                onStarshipsClick = { navController.navigate(AppRoutes.STARSHIPS) },
                onVehiclesClick = { navController.navigate(AppRoutes.VEHICLES) }
            )
        }

        // ----------------------------------------
        // PEOPLE LIST
        // ----------------------------------------
        composable(AppRoutes.PEOPLE) {
            PeopleListScreen(
                viewModel = peopleViewModel,
                onPersonClick = { id ->
                    navController.navigate("${AppRoutes.PERSON_DETAIL}/$id")
                }
            )
        }

        // ----------------------------------------
        // PERSON DETAIL
        // ----------------------------------------
        composable("${AppRoutes.PERSON_DETAIL}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            PersonDetailScreen(
                viewModel = personDetailViewModel,
                personId = id
            )
        }

        // ----------------------------------------
        // FILMS LIST
        // ----------------------------------------
        composable(AppRoutes.FILMS) {
            FilmsListScreen(
                navController = navController,
                viewModel = filmsViewModel
            )
        }

        // ----------------------------------------
        // FILM DETAIL
        // ----------------------------------------
        composable("${AppRoutes.FILM_DETAIL}/{id}") { backStackEntry ->
            val filmId = backStackEntry.arguments?.getString("id")?.toInt() ?: 1
            FilmDetailScreen(
                viewModel = filmDetailViewModel,
                filmId = filmId
            )
        }

        // ----------------------------------------
        // PLANETS
        // ----------------------------------------
        composable(AppRoutes.PLANETS) {
            PlanetsListScreen(viewModel = planetsViewModel)
        }

        // ----------------------------------------
        // STARSHIPS
        // ----------------------------------------
        composable(AppRoutes.STARSHIPS) {
            StarshipsListScreen(viewModel = starshipsViewModel)
        }

        // ----------------------------------------
        // VEHICLES
        // ----------------------------------------
        composable(AppRoutes.VEHICLES) {
            VehiclesListScreen(viewModel = vehiclesViewModel)
        }
    }
}
