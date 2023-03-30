package com.example.movieapp.elements

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.MovieInfo
import com.example.movieapp.models.Movie
import com.example.movieapp.ui.theme.LightGray

@Composable
fun MovieCard(movie: Movie, context: Context) {

    Card(
        elevation = 16.dp, modifier = Modifier
            .padding(horizontal = 8.dp).clickable(onClick = {
                val intent = Intent(context, MovieInfo::class.java)
                intent.putExtra("movie", movie)
                context.startActivity(intent)
            }), backgroundColor = Color.White.copy(alpha = 0f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(160.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = "${movie.vote_average.toString().slice(0..2)} / 10",
                    style = TextStyle(color = Color.White, fontSize = 13.sp),
                    modifier = Modifier.padding(start = 5.dp, top = 4.dp).align(Alignment.TopStart)
                )
            }
            Text(
                text = movie.title,
                style = (TextStyle(fontSize = 10.sp, color = LightGray)),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}