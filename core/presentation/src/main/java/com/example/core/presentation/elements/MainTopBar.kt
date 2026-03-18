package com.example.core.presentation.elements

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movies.presentation.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    titleText: String,
    iconVector: ImageVector,
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = "Menu", tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = titleText,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
                ),
            )
        },
        colors = TopAppBarColors(
            containerColor = Transparent,
            scrolledContainerColor = Transparent,
            navigationIconContentColor = Transparent,
            titleContentColor = Transparent,
            actionIconContentColor = Transparent,
            subtitleContentColor = Transparent
        )
    )
}