package com.example.starwarsapp.data.repository
import com.example.starwarsapp.utils.NetworkResult

suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        val response = apiCall()
        NetworkResult.Success(response)
    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "Error desconocido")
    }
}
