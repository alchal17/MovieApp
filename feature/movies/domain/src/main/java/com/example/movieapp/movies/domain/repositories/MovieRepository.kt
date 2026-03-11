package com.example.movieapp.movies.domain.repositories

import com.example.movieapp.movies.domain.Result
import com.example.movieapp.movies.domain.models.Movie


interface MovieRepository {
    suspend fun findByPage(pageNumber: Int): Result<List<Movie>>
}