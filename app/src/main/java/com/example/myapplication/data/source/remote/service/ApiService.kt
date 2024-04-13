package com.example.myapplication.data.source.remote.service

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(BuildConfig.BASE_APP_URL)
    suspend fun getRandomPersons(
        @Query("results") results: Int,
    ): PersonsResponse

    @GET(BuildConfig.BASE_APP_URL)
    suspend fun getRandomPerson(): PersonItemResponse

}