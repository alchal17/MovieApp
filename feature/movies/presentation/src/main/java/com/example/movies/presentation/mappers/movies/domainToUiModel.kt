package com.example.movies.presentation.mappers.movies

import com.example.movieapp.movies.domain.models.Movie
import com.example.movies.presentation.uiModel.MovieUiModel

internal fun Movie.toMovieUiModel(): MovieUiModel = MovieUiModel(
    id = this.id,
    overview = this.overview,
    posterPath = this.posterPath,
    title = this.title,
    originalLanguage = this.originalLanguage,
    releaseDate = this.releaseDate,
    voteAverage = this.voteAverage
)