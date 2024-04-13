package com.example.myapplication.di

import com.example.myapplication.data.repository.ApiRepositoryImpl
import com.example.myapplication.data.repository.CacheRepositoryImpl
import com.example.myapplication.data.source.local.database.AppDatabase
import com.example.myapplication.data.source.remote.service.ApiService
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.domain.repository.CacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideApiRepository(api: ApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCacheRepository(database: AppDatabase): CacheRepository {
        return CacheRepositoryImpl(database)
    }
}