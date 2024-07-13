package com.example.movieapp.hilt_modules

import com.example.movieapp.repositories.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMovieAPI(): MovieAPI = MovieAPI()
}