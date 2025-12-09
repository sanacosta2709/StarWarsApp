package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Planet
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanetsViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _planetsState = MutableStateFlow<NetworkResult<List<Planet>>>(NetworkResult.Loading)
    val planetsState: StateFlow<NetworkResult<List<Planet>>> = _planetsState

    init {
        loadPlanets()
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            _planetsState.value = NetworkResult.Loading
            _planetsState.value = repository.getPlanets()
        }
    }
}
