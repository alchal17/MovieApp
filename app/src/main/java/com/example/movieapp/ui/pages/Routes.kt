package com.example.movieapp.ui.pages

import kotlinx.serialization.Serializable

@Serializable
object MoviesListRoute

@Serializable
data class MovieInfoRoute(
    val posterPath: String,
    val id: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val originalLanguage: String,
)