package com.example.myapplication.domain.repository

import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse

interface ApiRepository {
    suspend fun getRandomPerson(): PersonItemResponse
    suspend fun getRandomPersons(results: Int): PersonsResponse
}