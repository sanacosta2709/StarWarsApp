package com.example.starwarsapp.data.repository

import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.model.Planet
import com.example.starwarsapp.data.model.Starship
import com.example.starwarsapp.data.model.Vehicle
import com.example.starwarsapp.utils.NetworkResult

interface StarWarsRepository {

    suspend fun getPeople(): NetworkResult<List<Person>>

    suspend fun getFilms(): NetworkResult<List<Film>>

    suspend fun getPlanets(): NetworkResult<List<Planet>>

    suspend fun getStarships(): NetworkResult<List<Starship>>

    suspend fun getVehicles(): NetworkResult<List<Vehicle>>

    suspend fun getPersonById(id: Int): NetworkResult<Person>

    suspend fun getFilmById(id: Int): NetworkResult<Film>



}
