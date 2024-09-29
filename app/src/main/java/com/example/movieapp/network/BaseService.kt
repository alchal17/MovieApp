package com.example.movieapp.network

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json


abstract class BaseService {
    protected val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json { ignoreUnknownKeys = true }
            )
        }
    }
    protected val serverPath = "https://api.themoviedb.org"

}