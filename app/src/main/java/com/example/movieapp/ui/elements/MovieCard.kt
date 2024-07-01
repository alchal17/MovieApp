package com.example.movieapp.ui.elements

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.models.Movie


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun SharedTransitionScope.MovieCard(
    movie: Movie,
    onClick: (
        posterPath: String,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onClick(
                    movie.posterPath,
                    movie.uniqueID,
                    movie.title,
                    movie.overview,
                    movie.releaseDate,
                    movie.originalLanguage
                )
            },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(160.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxSize()
                        .skipToLookaheadSize()
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${movie.uniqueID}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(500) }),
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = "${movie.voteAverage.toString().slice(0..2)} / 10",
                    style = TextStyle(color = Color.White, fontSize = 13.sp),
                    modifier = Modifier
                        .padding(start = 5.dp, top = 4.dp)
                        .align(Alignment.TopStart)
                )
            }
            Text(
                text = movie.title,
                style = (TextStyle(fontSize = 12.sp, color = Black)),
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 5.dp)
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "title/${movie.uniqueID}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { initialBounds, targetBounds ->
                            keyframes {
                                durationMillis = 500
                                initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                                targetBounds at 500
                            }
                        }
                    )
            )
        }
    }
}