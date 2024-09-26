package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieAPI
import com.example.movieapp.repositories.ServerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val apiRepository: MovieAPI) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _moviesFetching = MutableStateFlow(false)
    val moviesFetching = _moviesFetching.asStateFlow()

    private var currentPage = 0


    suspend fun addMovies() {
        _moviesFetching.value = true
        when (val response = apiRepository.getMovies(currentPage)) {
            is ServerResponse.Error -> {
                Log.d("error", response.message)
            }

            is ServerResponse.Success -> {
                _movies.value += response.results
                currentPage++
            }
        }
        _moviesFetching.value = false
    }
}