package com.example.movieapp.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.example.movieapp.models.Movie


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun SharedTransitionScope.MovieCard(
    movie: Movie,
    onClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .size(Size(40, 225))
            .scale(Scale.FILL)
            .build()
    )

    var visible by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = visible, enter = scaleIn()) {
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
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    if (imagePainter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Black
                        )
                    }
                    AsyncImage(
                        contentDescription = "Movie poster",
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                            .size(Size(40, 225)).scale(Scale.FILL).build(),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${movie.uniqueID}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ -> tween(500) })
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp, top = 4.dp)
                            .clip(shape = RoundedCornerShape(50))
                            .background(color = Black.copy(alpha = 0.7f)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                        Text(
                            text = "${movie.voteAverage.toString().slice(0..2)} / 10",
                            style = TextStyle(color = Color.White, fontSize = 13.sp),
                            modifier = Modifier
                        )
                    }
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

    LaunchedEffect(Unit) {
        visible = true
    }
}