package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _filmDetailState = MutableStateFlow<NetworkResult<Film>>(NetworkResult.Loading)
    val filmDetailState = _filmDetailState.asStateFlow()

    fun loadFilm(id: Int) {
        viewModelScope.launch {
            _filmDetailState.value = NetworkResult.Loading
            _filmDetailState.value = repository.getFilmById(id)
        }
    }
}
