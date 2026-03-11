package com.example.movies.data.mappers.movies

import com.example.movieapp.movies.domain.models.Movie
import com.example.movies.data.source.remote.dto.MovieDto

internal fun MovieDto.toMovie(): Movie = Movie(
    id = this.id,
    overview = this.overview,
    posterPath = this.posterPath,
    title = this.title,
    originalLanguage = this.originalLanguage,
    releaseDate = this.releaseDate,
    voteAverage = this.voteAverage
)