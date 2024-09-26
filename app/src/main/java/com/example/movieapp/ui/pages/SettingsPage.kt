package com.example.movieapp.ui.pages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.movieapp.datastores.ColumnsNumberDataStore
import kotlinx.coroutines.launch

@Composable
fun SettingsPage(context: Context) {
    val coroutineScope = rememberCoroutineScope()

    val selectedColumnsFlow = ColumnsNumberDataStore.getSelectedColumns(context)
    val selectedColumns by selectedColumnsFlow.collectAsState(initial = 3)

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Number of columns in table:", style = TextStyle(color = Color.White))
            (1..3).map {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black,
                        unselectedColor = Color.White
                    ),
                        selected = selectedColumns == it,
                        onClick = {
                            if (selectedColumns != it) {
                                coroutineScope.launch {
                                    ColumnsNumberDataStore.saveSelectedColumns(
                                        context,
                                        it
                                    )
                                }
                            }
                        })
                    Text(it.toString(), style = TextStyle(color = Color.White))
                }
            }
        }
    }
}