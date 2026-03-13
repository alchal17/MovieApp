package com.example.core.data.di

import com.example.core.data.source.local.sp.InnerStorage
import com.example.core.data.source.local.sp.InnerStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindInnerStorage(impl: InnerStorageImpl): InnerStorage
}