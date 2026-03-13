package com.example.core.data.di

import com.example.core.data.repositories.InnerStorageRepositoryImpl
import com.example.core.domain.repositories.InnerStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreDataModule {
    @Binds
    @Singleton
    abstract fun bindInnerStorage(impl: InnerStorageRepositoryImpl): InnerStorageRepository
}