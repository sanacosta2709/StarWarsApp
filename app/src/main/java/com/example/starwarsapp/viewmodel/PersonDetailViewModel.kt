package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.repository.StarWarsRepository
import com.example.starwarsapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonDetailViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _personDetailState = MutableStateFlow<NetworkResult<Person>>(NetworkResult.Loading)
    val personDetailState: StateFlow<NetworkResult<Person>> = _personDetailState

    fun loadPerson(id: Int) {
        viewModelScope.launch {
            _personDetailState.value = NetworkResult.Loading
            _personDetailState.value = repository.getPersonById(id)
        }
    }
}
