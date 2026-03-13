package com.example.movies.presentation.pages


sealed interface Routes {
    data object Movies: Routes
    data object Settings: Routes
}

