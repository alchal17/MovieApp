package com.example.movies.presentation.pages

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movies.presentation.uiUtils.MovieRoutes
import com.example.movies.presentation.viewmodels.MovieViewModel

@Composable
fun MoviesPage(navigateToSettings: () -> Unit) {
    val navController = rememberNavController()

    val movieViewModel = hiltViewModel<MovieViewModel>()

    val movies by movieViewModel.movies.collectAsStateWithLifecycle()
    val selectedColumn by movieViewModel.columnsNumber.collectAsStateWithLifecycle()
    val isLoading by movieViewModel.moviesFetching.collectAsStateWithLifecycle()

    val lazyVerticalGridState = rememberLazyGridState()

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = MovieRoutes.MovieList) {
            composable<MovieRoutes.MovieList> {
                MoviesListPage(
                    movies = movies,
                    selectedColumns = selectedColumn,
                    isLoading = isLoading,
                    moviesError = movieViewModel.moviesError,
                    lazyVerticalGridState = lazyVerticalGridState,
                    animatedVisibilityScope = this@composable,
                    addMovies = movieViewModel::addMovies,
                    cardOnClick = { posterPath, id, title, overview, releaseDate, originalLanguage ->
                        navController.navigate(
                            MovieRoutes.MovieInfo(
                                posterPath = posterPath,
                                id = id,
                                title = title,
                                overview = overview,
                                releaseDate = releaseDate,
                                originalLanguage = originalLanguage
                            )
                        )
                    },
                    navigateToSettings = navigateToSettings
                )
            }

            composable<MovieRoutes.MovieInfo> {
                val navArgs = it.toRoute<MovieRoutes.MovieInfo>()
                MovieInfo(
                    posterPath = navArgs.posterPath,
                    id = navArgs.id,
                    title = navArgs.title,
                    overview = navArgs.overview,
                    releaseDate = navArgs.releaseDate,
                    originalLanguage = navArgs.originalLanguage,
                    animatedVisibilityScope = this@composable,
                    floatingActionButtonClick = navController::navigateUp
                )
            }
        }
    }
}