package com.example.myapplication.di

import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.domain.repository.CacheRepository
import com.example.myapplication.domain.usecase.AddPersonCacheUseCase
import com.example.myapplication.domain.usecase.DeleteAllPersonsUseCase
import com.example.myapplication.domain.usecase.GetPersonItemUseCase
import com.example.myapplication.domain.usecase.GetPersonListCacheUseCase
import com.example.myapplication.domain.usecase.GetPersonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPersonItemUseCase(repository: ApiRepository) = GetPersonItemUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPersonsUseCase(repository: ApiRepository) = GetPersonsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPersonListCacheUseCase(repository: CacheRepository) = GetPersonListCacheUseCase(repository)

    @Provides
    @Singleton
    fun provideAddPersonCacheUseCase(repository: CacheRepository) = AddPersonCacheUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteAllPersonUseCase(repository: CacheRepository) = DeleteAllPersonsUseCase(repository)
}