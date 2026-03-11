package com.example.movieapp.data.api

interface PaginationApi<T> {
    suspend fun getByPage(pageNumber: Int): T
}