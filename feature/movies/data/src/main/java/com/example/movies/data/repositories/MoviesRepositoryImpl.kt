package com.example.movies.data.repositories

import com.example.movieapp.movies.domain.Result
import com.example.movieapp.movies.domain.models.Movie
import com.example.movieapp.movies.domain.repositories.MovieRepository
import com.example.movies.data.mappers.movies.toMovie
import com.example.movies.data.source.remote.api.MoviesService
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(private val moviesService: MoviesService) :
    MovieRepository {
    override suspend fun findByPage(pageNumber: Int): Result<List<Movie>> {
        return when (val result = moviesService.getByPage(1)) {
            is Result.Error -> result
            is Result.Success -> {
                val dataMovies = result.data
                val domainMovies = dataMovies.map { it.toMovie() }
                Result.Success(domainMovies)
            }
        }
    }
}