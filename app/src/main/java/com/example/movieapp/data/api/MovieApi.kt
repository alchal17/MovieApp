package com.example.movieapp.data.api

import com.example.movieapp.data.Constants
import com.example.movieapp.data.dto.MovieDto
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MovieApi : BaseApi(), PaginationApi<ApiResponse<MovieDto>> {

    @Serializable
    private data class MovieResponse(
        @SerialName("results") val results: List<MovieDto>
    )

    override suspend fun getByPage(pageNumber: Int): ApiResponse<MovieDto> {
        return try {
            val response: MovieResponse =
                client.get("${serverPath}/3/trending/movie/day?api_key=${Constants.API_KEY}&page=${pageNumber + 1}")
            ApiResponse.Success(response.results)
        } catch (e: Exception) {
            return ApiResponse.Error(e.localizedMessage ?: "Unknown error")
        }
    }

}