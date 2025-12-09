package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import com.example.starwarsapp.data.model.Starship
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StarshipsViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _starshipsState = MutableStateFlow<NetworkResult<List<Starship>>>(NetworkResult.Loading)
    val starshipsState: StateFlow<NetworkResult<List<Starship>>> = _starshipsState

    init {
        loadStarships()
    }

    private fun loadStarships() {
        viewModelScope.launch {
            _starshipsState.value = NetworkResult.Loading
            _starshipsState.value = repository.getStarships()
        }
    }
}
