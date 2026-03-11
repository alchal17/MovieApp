package com.example.movieapp.movies.domain.useCases

import com.example.movieapp.movies.domain.Result
import com.example.movieapp.movies.domain.models.Movie
import com.example.movieapp.movies.domain.repositories.MovieRepository
import javax.inject.Inject

class GetMoviesByPageUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int): Result<List<Movie>> =
        movieRepository.findByPage(pageNumber)
}