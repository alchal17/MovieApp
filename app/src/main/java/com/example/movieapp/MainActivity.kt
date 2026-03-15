package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.theme.MovieAppTheme
import com.example.movieapp.presentation.uiUtils.Routes
import com.example.movies.presentation.pages.MoviesPage
import com.example.settings.presentation.pages.SettingsPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovieAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.Movies
                ) {
                    composable<Routes.Movies> { MoviesPage() }
                    composable<Routes.Settings> { SettingsPage() }
                }
            }
        }
    }
}
