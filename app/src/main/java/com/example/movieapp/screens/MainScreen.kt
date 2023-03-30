package com.example.movieapp.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.elements.MovieCard
import com.example.movieapp.models.Movie
import com.example.movieapp.scripts.changeMoviesState
import com.example.movieapp.ui.theme.DarkGray
import com.example.movieapp.ui.theme.LightGray

@Composable
fun MainScreen(context: Context) {
    val movieState = remember {
        mutableStateOf(arrayOf<Movie>())
    }
    changeMoviesState(context = LocalContext.current, state = movieState, page = 1)
    Box(modifier = Modifier.background(DarkGray)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Featured",
                    style = TextStyle(
                        color = LightGray,
                        fontSize = 35.sp,
                        fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(movieState.value) { movie -> MovieCard(movie = movie, context) }
            }
        }
    }
}