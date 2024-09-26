package com.example.movieapp.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    coroutineScope: CoroutineScope,
    currentTitleIndex: Int,
    onNavigationIconClick: suspend () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { onNavigationIconClick() } }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu", tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = listOf("Featured Films", "Settings")[currentTitleIndex],
                style = TextStyle(
                    color = LightGray,
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
                ),
            )
        },
        colors = TopAppBarColors(
            containerColor = Transparent,
            titleContentColor = LightGray,
            actionIconContentColor = Transparent,
            navigationIconContentColor = Transparent, scrolledContainerColor = Transparent
        )
    )
}