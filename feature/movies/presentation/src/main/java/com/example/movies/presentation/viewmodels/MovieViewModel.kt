package com.example.movies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetColumnsNumberUseCase
import com.example.movieapp.movies.domain.Result
import com.example.movieapp.movies.domain.useCases.GetMoviesByPageUseCase
import com.example.movies.presentation.mappers.movies.toMovieUiModel
import com.example.movies.presentation.uiModel.MovieUiModel
import com.example.movies.presentation.uiStates.MoviesError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
internal class MovieViewModel @Inject constructor(
    private val getMoviesByPageUseCase: GetMoviesByPageUseCase,
    private val getColumnsNumberUseCase: GetColumnsNumberUseCase
) :
    ViewModel() {
    private val _moviesError = MutableSharedFlow<MoviesError>()
    val moviesError = _moviesError.asSharedFlow()
    private val _movies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movies: StateFlow<List<MovieUiModel>> = _movies.asStateFlow()

    private val _moviesFetching = MutableStateFlow(false)
    val moviesFetching = _moviesFetching.asStateFlow()

    private val _columnsNumber = MutableStateFlow(3)
    val columnsNumber = _columnsNumber.asStateFlow()

    private val currentPage = AtomicInteger(1)

    init {
        viewModelScope.launch {
            val savedColumnsNumber = getColumnsNumberUseCase()
            _columnsNumber.update { savedColumnsNumber }
        }
        viewModelScope.launch {
            addMovies()
        }
    }

    fun addMovies() {
        if (_moviesFetching.value) return

        viewModelScope.launch {
            _moviesFetching.update { true }
            when (val result = getMoviesByPageUseCase(currentPage.get())) {
                is Result.Error -> {
                    _moviesError.emit(MoviesError(result.message))
                }

                is Result.Success -> {
                    val moviesUiModel = result.data.map { it.toMovieUiModel() }
                    _movies.update { it + moviesUiModel }
                    currentPage.incrementAndGet()
                }
            }
            _moviesFetching.update { false }
        }
    }
}