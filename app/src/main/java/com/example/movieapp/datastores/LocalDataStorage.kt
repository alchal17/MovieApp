package com.example.movieapp.datastores

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface LocalDataStorage<T> {
    suspend fun save(context: Context, value: T)

    fun get(context: Context): Flow<T>
}