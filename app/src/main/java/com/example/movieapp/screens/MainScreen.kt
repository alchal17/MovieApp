package com.example.movieapp.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movieapp.API_KEY
import com.example.movieapp.R
import com.example.movieapp.elements.MovieCard
import com.example.movieapp.models.Movie
import com.example.movieapp.scripts.*
import com.example.movieapp.ui.theme.DarkGray
import com.example.movieapp.ui.theme.LightGray
import com.google.gson.Gson
import org.json.JSONObject


@Composable
fun MainScreen(context: Context) {
    val pages = remember {
        mutableStateOf(1)
    }
    val movieState = remember {
        mutableStateOf(mutableListOf<Movie>())
    }

    val firstAdded = remember {
        mutableStateOf(true)
    }
    val topText = remember {
        mutableStateOf("")
    }

    val listState = rememberLazyGridState()
    val totalItemCount = listState.layoutInfo.totalItemsCount

    if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == movieState.value.size - 1 && totalItemCount != 0) {
        if (isInternetAvailable(context)) {
            topText.value = "Featured"
            pages.value++
            movieState.value.addAll(getMoviesByPage(context = context, page = pages.value))
        }
        else{
            topText.value = "No Internet connection"
        }
    }
    if (totalItemCount == 0 && firstAdded.value) {
        if (isInternetAvailable(context)) {
            topText.value = "Featured"
            val url =
                "https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY&page=${pages.value}"
            val queue = Volley.newRequestQueue(context)
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val obj = JSONObject(response.trimIndent())
                val jsonArr = obj.getJSONArray("results").toString().trimIndent()
                val gson = Gson()
                val movies = gson.fromJson(jsonArr, Array<Movie>::class.java)
                movieState.value = movies.toMutableList()
            }, { error ->
                Log.d("MyLog", "Error")
            })
            queue.add(stringRequest)
            firstAdded.value = false
        }
        else{
            topText.value = "No Internet connection"
        }
    }
    Box(modifier = Modifier.background(DarkGray)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = topText.value, style = TextStyle(
                        color = LightGray,
                        fontSize = 35.sp,
                        fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
                    ), modifier = Modifier.align(Alignment.Center)
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                state = listState,
            ) {
                items(movieState.value) { movie -> MovieCard(movie = movie, context) }
            }
        }
    }
}