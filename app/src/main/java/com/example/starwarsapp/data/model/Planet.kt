package com.example.starwarsapp.data.model

import com.google.gson.annotations.SerializedName

data class Planet(
    val name: String,
    val climate: String,
    val terrain: String,
    @SerializedName("population") val population: String
)
