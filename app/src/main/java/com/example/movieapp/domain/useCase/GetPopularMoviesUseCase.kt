package com.example.movieapp.domain.useCase

import com.example.movieapp.data.api.ApiResponse
import com.example.movieapp.data.api.PaginationApi
import com.example.movieapp.data.dto.MovieDto
import com.example.movieapp.data.mapper.toDomain
import com.example.movies.domain.models.Movie

class GetPopularMoviesUseCase(private val movieApi: PaginationApi<ApiResponse<List<MovieDto>>>) {

    suspend fun getByPage(page: Int): ApiResponse<List<Movie>> =
        when (val response = movieApi.getByPage(page)) {
            is ApiResponse.Error -> ApiResponse.Error(response.message)
            is ApiResponse.Success<List<MovieDto>> -> ApiResponse.Success(response.result.toDomain())
        }
}