package com.example.settings.presentation.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.settings.presentation.viewmodels.SettingsViewModel

@Composable
fun SettingsPage() {
    val settingsViewModel = hiltViewModel<SettingsViewModel>()

    val selectedColumns by settingsViewModel.currentColumnNumber.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Number of columns in table:", style = TextStyle(color = Color.White))
            (1..3).forEach {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.White
                        ),
                        selected = selectedColumns == it,
                        onClick = {
                            if (selectedColumns != it) {
                                settingsViewModel.setColumnsNumber(it)
                            }
                        })
                    Text(it.toString(), style = TextStyle(color = Color.White))
                }
            }
        }
    }
}