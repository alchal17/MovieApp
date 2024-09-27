package com.example.movieapp.repositories

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

sealed interface ServerResponse<out T> {
    data class Success<T>(val results: List<T>) : ServerResponse<T>
    data class Error(val message: String) : ServerResponse<Nothing>
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