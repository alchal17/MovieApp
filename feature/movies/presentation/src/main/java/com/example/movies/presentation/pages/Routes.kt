package com.example.movies.presentation.pages

import kotlinx.serialization.Serializable


sealed interface Routes {
    @Serializable
    data object Movies : Routes

    @Serializable
    data object Settings : Routes
}