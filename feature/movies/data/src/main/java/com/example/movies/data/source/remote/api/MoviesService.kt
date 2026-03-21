package com.example.movies.data.source.remote.api

import com.example.movieapp.movies.domain.Result
import com.example.movies.data.DataConstants
import com.example.movies.data.source.remote.dto.MovieDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

internal class MoviesService @Inject constructor(private val client: HttpClient) {

    @Serializable
    private data class MovieResponse(
        @SerialName("results") val results: List<MovieDto>
    )

    suspend fun getByPage(pageNumber: Int): Result<List<MovieDto>> =
        withContext(Dispatchers.IO) {
            try {
                val movies =
                    client.get("${DataConstants.SERVER_PATH}/3/trending/movie/day?api_key=${DataConstants.API_KEY}&page=$pageNumber")
                        .body<MovieResponse>()
                Result.Success(movies.results)
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error occured.")
            }
        }

}