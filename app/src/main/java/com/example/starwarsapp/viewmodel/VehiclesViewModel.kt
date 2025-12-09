package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Vehicle
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VehiclesViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _vehiclesState = MutableStateFlow<NetworkResult<List<Vehicle>>>(NetworkResult.Loading)
    val vehiclesState: StateFlow<NetworkResult<List<Vehicle>>> = _vehiclesState

    init {
        loadVehicles()
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            _vehiclesState.value = NetworkResult.Loading
            _vehiclesState.value = repository.getVehicles()
        }
    }
}
