package com.example.movies.presentation.uiUtils

import kotlinx.serialization.Serializable

internal sealed interface MovieRoutes {
    @Serializable
    data object MovieList : MovieRoutes

    @Serializable
    data class MovieInfo(
        val posterPath: String?,
        val id: String,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val originalLanguage: String,
    ) : MovieRoutes
}