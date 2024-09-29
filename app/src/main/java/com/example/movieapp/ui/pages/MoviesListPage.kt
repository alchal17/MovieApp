package com.example.movieapp.ui.pages

import android.content.Context
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.datastores.LocalDataStorage
import com.example.movieapp.ui.elements.DetailedMovieCard
import com.example.movieapp.ui.elements.MovieCard
import com.example.movieapp.viewmodels.MovieViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesListPage(
    context: Context,
    moviesViewModel: MovieViewModel,
    lazyVerticalGridState: LazyGridState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    localDataStorage: LocalDataStorage<Int>,
    cardOnClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
) {
    val movies by moviesViewModel.movies.collectAsState()
    val selectedColumnsFlow = localDataStorage.get(context)
    val selectedColumns by selectedColumnsFlow.collectAsState(initial = 3)
    val isLoading = moviesViewModel.moviesFetching.collectAsState()

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
            key = { index -> movies[index].uniqueID }) {
            Box(modifier = Modifier.height(200.dp)) {
                if (selectedColumns == 1) {
                    DetailedMovieCard(movies[it])
                } else {
                    MovieCard(
                        movie = movies[it],
                        onClick = cardOnClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }
    LaunchedEffect(isAtEndOfList && !isLoading.value) {
        moviesViewModel.addMovies()
    }
}