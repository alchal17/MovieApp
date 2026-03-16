package com.example.movies.presentation.pages

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.movies.presentation.elements.DetailedMovieCard
import com.example.movies.presentation.elements.MovieCard
import com.example.movies.presentation.uiModel.MovieUiModel
import com.example.movies.presentation.uiStates.MoviesError
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.MoviesListPage(
    movies: List<MovieUiModel>,
    selectedColumns: Int,
    isLoading: Boolean,
    moviesError: Flow<MoviesError>,
    lazyVerticalGridState: LazyGridState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    addMovies: () -> Unit,
    cardOnClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
) {

    val context = LocalContext.current

    val isAtEndOfList by remember {
        derivedStateOf {
            val lastVisibleIndex =
                lazyVerticalGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleIndex == movies.size - 1
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(selectedColumns),
        state = lazyVerticalGridState,
        verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.fillMaxSize()
    ) {
        items(
            movies.size,
            key = { index -> movies[index].uuid }) {
            Box(modifier = Modifier.height(200.dp)) {
                if (selectedColumns == 1) {
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        DetailedMovieCard(
                            movieDto = movies[it],
                            onClick = cardOnClick,
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                } else {
                    MovieCard(
                        movieDto = movies[it],
                        onClick = cardOnClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }
    LaunchedEffect(isAtEndOfList && !isLoading) {
        addMovies()
    }
    LaunchedEffect(Unit) {
        moviesError.collect { error ->
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}