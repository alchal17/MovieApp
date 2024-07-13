package com.example.movieapp.repositories

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

sealed class ServerResponse<T> {
    data class Success<T>(val results: List<T>) : ServerResponse<T>()
    data class Error<T>(val message: String) : ServerResponse<T>()
}

abstract class BaseAPI {
    protected val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json { ignoreUnknownKeys = true }
            )
        }
    }
    protected val serverPath = "https://api.themoviedb.org"

}