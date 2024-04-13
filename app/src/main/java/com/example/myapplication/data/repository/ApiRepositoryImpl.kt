package com.example.myapplication.data.repository

import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse
import com.example.myapplication.data.source.remote.service.ApiService
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.common.Constants.DEFAULT_NUMBER_OF_PERSONS

class ApiRepositoryImpl(
    private val apiService: ApiService
): ApiRepository {
    override suspend fun getRandomPerson(): PersonItemResponse {
        return apiService.getRandomPerson()
    }

    override suspend fun getRandomPersons(results: Int): PersonsResponse {
        return apiService.getRandomPersons(DEFAULT_NUMBER_OF_PERSONS)
    }
}