package com.example.movieapp.hilt_modules

import com.example.movieapp.datastores.ColumnsNumberDataStorage
import com.example.movieapp.datastores.LocalDataStorage
import com.example.movieapp.network.BaseMovieService
import com.example.movieapp.network.MovieService
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
    fun provideMovieService(): BaseMovieService = MovieService()

    @Provides
    @Singleton
    @Named("ColumnsDataStorage")
    fun provideColumnsDataStorage(): LocalDataStorage<Int> = ColumnsNumberDataStorage()
}