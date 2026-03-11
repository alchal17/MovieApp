package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.MovieDto
import com.example.movies.domain.models.Movie

fun MovieDto.toDomain(): Movie = Movie(
    overview = overview,
    posterPath = posterPath,
    title = title,
    originalLanguage = originalLanguage,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)

fun List<MovieDto>.toDomain(): List<Movie> = this.map { it.toDomain() }