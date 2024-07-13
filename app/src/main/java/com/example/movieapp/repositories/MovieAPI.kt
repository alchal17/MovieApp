package com.example.movieapp.repositories

import com.example.movieapp.API_KEY
import com.example.movieapp.models.Movie
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MovieAPI : BaseAPI() {

    @Serializable
    private data class MovieResponse(
        @SerialName("results") val results: List<Movie>
    )

    suspend fun getMovies(pageNumber: Int): ServerResponse<Movie> {
        return try {
            val response: MovieResponse =
                client.get("${serverPath}/3/trending/movie/day?api_key=$API_KEY&page=${pageNumber + 1}")
            ServerResponse.Success(response.results)
        } catch (e: Exception) {
            return ServerResponse.Error(e.localizedMessage)
        }
    }

}