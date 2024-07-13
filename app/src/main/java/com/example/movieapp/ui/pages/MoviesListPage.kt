package com.example.movieapp.ui.pages

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.MainActivity
import com.example.movieapp.ui.elements.MainTopBar
import com.example.movieapp.ui.elements.MovieCard


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesListPage(
    context: MainActivity,
    animatedVisibilityScope: AnimatedVisibilityScope,
    cardOnClick: (
        posterPath: String,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit
) {
    val moviesViewModel = context.moviesViewModel
    Scaffold(topBar = { MainTopBar() }, containerColor = Color.DarkGray) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                state = context.lazyVerticalGridState,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(
                    moviesViewModel.movies.size,
                    key = { index -> moviesViewModel.movies[index].uniqueID }) {
                    MovieCard(
                        movie = moviesViewModel.movies[it],
                        onClick = cardOnClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }
    LaunchedEffect(context.lazyVerticalGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == moviesViewModel.movies.size - 1 && !moviesViewModel.moviesFetching.value) {
        moviesViewModel.addMovies()
    }
}