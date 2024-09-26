package com.example.movieapp.ui.pages

import kotlinx.serialization.Serializable


sealed interface Routes {

    sealed interface MainScaffoldRoutes : Routes {

        @Serializable
        data object Settings : MainScaffoldRoutes

        @Serializable
        data object MovieList : MainScaffoldRoutes
    }

    @Serializable
    data object MainScaffold

    @Serializable
    data class MovieInfo(
        val posterPath: String?,
        val id: String,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val originalLanguage: String,
    ) : Routes
}

