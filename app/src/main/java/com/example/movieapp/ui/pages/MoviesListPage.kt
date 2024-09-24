package com.example.movieapp.ui.pages

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.elements.MainTopBar
import com.example.movieapp.ui.elements.MovieCard
import com.example.movieapp.viewmodels.MovieViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesListPage(
    moviesViewModel: MovieViewModel,
    lazyVerticalGridState: LazyGridState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    cardOnClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit
) {
    val movies by moviesViewModel.movies.collectAsState()
    Scaffold(topBar = { MainTopBar() }, containerColor = Color.DarkGray) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                state = lazyVerticalGridState,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(
                    movies.size,
                    key = { index -> movies[index].uniqueID }) {
                    MovieCard(
                        movie = movies[it],
                        onClick = cardOnClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }
    LaunchedEffect(lazyVerticalGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == movies.size - 1 && !moviesViewModel.moviesFetching.value) {
        moviesViewModel.addMovies()
    }
}