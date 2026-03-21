package com.example.settings.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.presentation.elements.MainTopBar
import com.example.settings.presentation.R
import com.example.settings.presentation.elements.ColumnsAmountElement
import com.example.settings.presentation.viewmodels.SettingsViewModel

@Preview
@Composable
fun SettingsPage(navigateToMovies: () -> Unit = {}) {
    val settingsViewModel = hiltViewModel<SettingsViewModel>()

    val selectedColumns by settingsViewModel.currentColumnNumber.collectAsStateWithLifecycle()

    Scaffold(containerColor = Color.Black.copy(alpha = 0.5f), topBar = {
        MainTopBar(
            titleText = "Settings",
            iconVector = Icons.Default.Movie,
            onNavigationIconClick = navigateToMovies
        )
    }) { scaffoldPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Cards Columns:",
                    fontFamily = FontFamily(Font(R.font.oswald_semi_bold)),
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(0.55f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { index ->
                        ColumnsAmountElement(
                            number = index + 1,
                            chosen = index + 1 == selectedColumns
                        ) { settingsViewModel.setColumnsNumber(index + 1) }
                    }
                }
            }
        }
    }
}