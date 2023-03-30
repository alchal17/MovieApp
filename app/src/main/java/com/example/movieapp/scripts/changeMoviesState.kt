package com.example.movieapp.scripts

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movieapp.API_KEY
import com.example.movieapp.models.Movie
import com.google.gson.Gson
import org.json.JSONObject

fun changeMoviesState(context: Context, state: MutableState<Array<Movie>>, page: Int) {
    val url = "https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY&page=$page"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url, { response ->
            val obj = JSONObject(response.trimIndent())
            val jsonArr = obj.getJSONArray("results").toString().trimIndent()
            val gson = Gson()
            val movies = gson.fromJson(jsonArr, Array<Movie>::class.java)
            state.value = movies
        },
        { error ->
            Log.d("MyLog", "Error")
        }
    )
    queue.add(stringRequest)
}