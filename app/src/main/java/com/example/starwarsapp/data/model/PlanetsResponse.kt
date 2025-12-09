package com.example.starwarsapp.data.model

data class PlanetsResponse(
    val count: Int,
    val results: List<Planet>
)