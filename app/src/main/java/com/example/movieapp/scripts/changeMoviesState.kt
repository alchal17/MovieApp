package com.example.movieapp.scripts

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.runtime.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movieapp.API_KEY
import com.example.movieapp.models.Movie
import com.google.gson.Gson
import org.json.JSONObject

@Composable
fun getMoviesByPage(context: Context, page: Int): Array<Movie> {
    val url = "https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY&page=$page"
    val movieState = remember {
        mutableStateOf(arrayOf<Movie>())
    }
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url, { response ->
            val obj = JSONObject(response.trimIndent())
            val jsonArr = obj.getJSONArray("results").toString().trimIndent()
            val gson = Gson()
            val movies = gson.fromJson(jsonArr, Array<Movie>::class.java)
            movieState.value = movies
        },
        { error ->
            Log.d("MyLog", "Error")
        }
    )
    queue.add(stringRequest)
    return movieState.value
}

fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return result
}