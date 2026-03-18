package com.example.settings.presentation.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.presentation.elements.MainTopBar
import com.example.settings.presentation.viewmodels.SettingsViewModel

@Composable
fun SettingsPage(navigateToMovies: () -> Unit) {
    val settingsViewModel = hiltViewModel<SettingsViewModel>()

    val selectedColumns by settingsViewModel.currentColumnNumber.collectAsStateWithLifecycle()

    //Column range: 1-3

    Scaffold(containerColor = Color.Black.copy(alpha = 0.5f), topBar = {
        MainTopBar(
            titleText = "Settings",
            iconVector = Icons.Default.Movie,
            onNavigationIconClick = navigateToMovies
        )
    }) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) { }
    }
}