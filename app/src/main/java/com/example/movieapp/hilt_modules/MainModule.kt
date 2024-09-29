package com.example.movieapp.hilt_modules

import com.example.movieapp.datastores.ColumnsNumberDataStorage
import com.example.movieapp.datastores.LocalDataStorage
import com.example.movieapp.models.Movie
import com.example.movieapp.network.MovieService
import com.example.movieapp.network.PaginationService
import com.example.movieapp.network.ServerResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    @Named("MovieService")
    fun providePaginationService(): PaginationService<ServerResponse<Movie>> = MovieService()

    @Provides
    @Singleton
    @Named("ColumnsDataStorage")
    fun provideColumnsDataStorage(): LocalDataStorage<Int> = ColumnsNumberDataStorage()
}