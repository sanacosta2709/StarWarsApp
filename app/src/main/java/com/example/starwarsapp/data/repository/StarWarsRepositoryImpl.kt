package com.example.starwarsapp.data.repository

import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.data.model.FilmsResponse
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.model.PeopleResponse
import com.example.starwarsapp.data.model.Planet
import com.example.starwarsapp.data.model.Starship
import com.example.starwarsapp.data.model.Vehicle
import com.example.starwarsapp.data.remote.StarWarsApiService
import com.example.starwarsapp.utils.NetworkResult
import retrofit2.Response

class StarWarsRepositoryImpl(
    private val apiService: StarWarsApiService
) : StarWarsRepository {

    //-----------------------------------------
    // PEOPLE
    //-----------------------------------------
    override suspend fun getPeople(): NetworkResult<List<Person>> {
        return safeApiCall { apiService.getPeople() }
            .mapSuccess { it.results }
    }

    //-----------------------------------------
    // FILMS
    //-----------------------------------------
    override suspend fun getFilms(): NetworkResult<List<Film>> {
        return safeApiCall { apiService.getFilms() }
            .mapSuccess { it.results }
    }

    override suspend fun getPlanets(): NetworkResult<List<Planet>> {
        return safeApiCall { apiService.getPlanets() }
            .mapSuccess { it.results }
    }
    override suspend fun getStarships(): NetworkResult<List<Starship>> {
        return safeApiCall { apiService.getStarships() }
            .mapSuccess { it.results }
    }
    override suspend fun getVehicles(): NetworkResult<List<Vehicle>> {
        return safeApiCall { apiService.getVehicles() }
            .mapSuccess { it.results }
    }
    override suspend fun getPersonById(id: Int): NetworkResult<Person> {
        return safeApiCall { apiService.getPersonById(id) }
    }

    override suspend fun getFilmById(id: Int): NetworkResult<Film> {
        return safeApiCall { apiService.getFilmById(id) }
    }





    //-----------------------------------------
    // safeApiCall (GENÉRICO)
    //-----------------------------------------
    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error("Respuesta vacía del servidor")
                }
            } else {
                NetworkResult.Error("Error HTTP ${response.code()}: ${response.message()}")
            }

        } catch (e: Exception) {
            NetworkResult.Error("Error: ${e.localizedMessage ?: "Error inesperado"}")
        }
    }

    //-----------------------------------------
    // Helper: mapSuccess() convierte un Success<T> en Success<R>
    //-----------------------------------------
    private inline fun <T, R> NetworkResult<T>.mapSuccess(
        transform: (T) -> R
    ): NetworkResult<R> {
        return when (this) {
            is NetworkResult.Success -> NetworkResult.Success(transform(data))
            is NetworkResult.Error -> NetworkResult.Error(message)
            is NetworkResult.Loading -> NetworkResult.Loading

        }
    }
}
