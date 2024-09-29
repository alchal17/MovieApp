package com.example.movieapp.network

import com.example.movieapp.models.Movie

abstract class BaseMovieService : PaginationService<ServerResponse<Movie>>, BaseService()