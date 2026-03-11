package com.example.movieapp.movies.domain.models

data class Movie(
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val title: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
)