package com.example.movieapp.models

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Immutable
@Serializable
data class Movie(
    val uniqueID: String = UUID.randomUUID().toString(),
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("title") val title: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val voteAverage: Double,
)
