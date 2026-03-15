package com.example.movies.presentation.uiStates

sealed interface MoviesResult {
    data object Success : MoviesResult
    data class Error(val message: String) : MoviesResult
}