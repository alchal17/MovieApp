package com.example.movies.presentation.uiModel

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
internal data class MovieUiModel(
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val title: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
) {
    @OptIn(ExperimentalUuidApi::class)
    val uuid = Uuid.generateV7().toString()
}