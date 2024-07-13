package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieapp.ui.pages.MovieInfo
import com.example.movieapp.ui.pages.MovieInfoRoute
import com.example.movieapp.ui.pages.MoviesListPage
import com.example.movieapp.ui.pages.MoviesListRoute
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var lazyVerticalGridState: LazyGridState
    lateinit var moviesViewModel: MovieViewModel

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            moviesViewModel = hiltViewModel()
            lazyVerticalGridState = rememberLazyGridState()
            val navController = rememberNavController()
            MovieAppTheme {
                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = MoviesListRoute) {
                        composable<MoviesListRoute> {
                            MoviesListPage(
                                context = this@MainActivity,
                                animatedVisibilityScope = this,
                                cardOnClick = { posterPath: String,
                                                id: String,
                                                title: String,
                                                overview: String,
                                                releaseDate: String,
                                                originalLanguage: String ->
                                    navController.navigate(
                                        MovieInfoRoute(
                                            posterPath,
                                            id,
                                            title,
                                            overview,
                                            releaseDate,
                                            originalLanguage
                                        )
                                    )
                                }
                            )
                        }
                        composable<MovieInfoRoute> {
                            val args = it.toRoute<MovieInfoRoute>()
                            MovieInfo(
                                posterPath = args.posterPath,
                                id = args.id,
                                title = args.title,
                                overview = args.overview,
                                releaseDate = args.releaseDate,
                                originalLanguage = args.originalLanguage,
                                animatedVisibilityScope = this
                            )
                        }
                    }
                }
            }
        }
    }
}
