package com.example.movieapp.network

interface PaginationService<T> {
    suspend fun getByPage(pageNumber: Int): T
}