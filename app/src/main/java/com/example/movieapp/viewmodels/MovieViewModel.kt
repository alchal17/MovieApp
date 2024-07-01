package com.example.movieapp.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.example.movieapp.API_KEY
import com.example.movieapp.models.Movie
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MovieViewModel : BaseViewModel() {
    private val _movies = mutableStateListOf<Movie>()
    val movies: SnapshotStateList<Movie> get() = _movies

    private val _moviesFetching = mutableStateOf(false)
    val moviesFetching: MutableState<Boolean> get() = _moviesFetching

    private var currentPage = 0

    @Serializable
    private data class MovieResponse(
        @SerialName("results") val results: List<Movie>
    )

    suspend fun fetchMovies() {
        moviesFetching.value = true
        try {
            val response: MovieResponse =
                client.get("$serverPath/3/trending/movie/day?api_key=$API_KEY&page=${currentPage + 1}")
            _movies.addAll(response.results)
            currentPage++
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage ?: "Unknown error")
        } finally {
            moviesFetching.value = false
        }
    }
}