package com.example.movieapp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json


abstract class BaseApi {
    protected val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json { ignoreUnknownKeys = true }
            )
        }
    }
    protected val serverPath = "https://api.themoviedb.org"

}