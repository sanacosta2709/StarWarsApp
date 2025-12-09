package com.example.starwarsapp.data.remote

import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.data.model.FilmsResponse
import com.example.starwarsapp.data.model.PeopleResponse
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.model.PlanetsResponse
import com.example.starwarsapp.data.model.StarshipsResponse
import com.example.starwarsapp.data.model.VehiclesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiService {

    @GET("people/")
    suspend fun getPeople(): Response<PeopleResponse>

    @GET("films/")
    suspend fun getFilms(): Response<FilmsResponse>

    @GET("planets/")
    suspend fun getPlanets(): Response<PlanetsResponse>

    @GET("starships/")
    suspend fun getStarships(): Response<StarshipsResponse>

    @GET("vehicles/")
    suspend fun getVehicles(): Response<VehiclesResponse>

    @GET("people/{id}/")
    suspend fun getPersonById(@Path("id") id: Int): Response<Person>

    @GET("films/{id}/")
    suspend fun getFilmById(@Path("id") id: Int): Response<Film>

}

