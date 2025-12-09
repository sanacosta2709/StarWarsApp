package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _peopleState = MutableStateFlow<NetworkResult<List<Person>>>(NetworkResult.Loading)
    val peopleState: StateFlow<NetworkResult<List<Person>>> = _peopleState

    init {
        loadPeople()
    }

    private fun loadPeople() {
        viewModelScope.launch {
            _peopleState.value = NetworkResult.Loading
            _peopleState.value = repository.getPeople()
        }
    }
}
