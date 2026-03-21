package com.example.movies.presentation.uiModel

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
internal data class MovieUiModel(
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val title: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
    val uuid: String = UUID.randomUUID().toString()
)