package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieapp.datastores.LocalDataStorage
import com.example.movieapp.ui.elements.MainScaffold
import com.example.movieapp.ui.pages.MovieInfo
import com.example.movieapp.ui.pages.Routes
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named("ColumnsDataStorage")
    lateinit var localDataStorage: LocalDataStorage<Int>

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val movieViewModel = hiltViewModel<MovieViewModel>()
            val navController = rememberNavController()
            MovieAppTheme {
                SharedTransitionLayout {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MainScaffold
                    ) {
                        composable<Routes.MainScaffold> {
                            MainScaffold(
                                animatedVisibilityScope = this,
                                movieViewModel = movieViewModel,
                                mainNavController = navController,
                                localDataStorage = localDataStorage
                            )
                        }
                        composable<Routes.MovieInfo> {
                            val args = it.toRoute<Routes.MovieInfo>()
                            MovieInfo(
                                posterPath = args.posterPath,
                                id = args.id,
                                title = args.title,
                                overview = args.overview,
                                releaseDate = args.releaseDate,
                                originalLanguage = args.originalLanguage,
                                animatedVisibilityScope = this,
                                floatingActionButtonClick = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}
