package com.example.starwarsapp.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Film
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _filmsState = MutableStateFlow<NetworkResult<List<Film>>>(NetworkResult.Loading)
    val filmsState: StateFlow<NetworkResult<List<Film>>> = _filmsState

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch {
            _filmsState.value = NetworkResult.Loading
            _filmsState.value = repository.getFilms()
        }
    }
}
